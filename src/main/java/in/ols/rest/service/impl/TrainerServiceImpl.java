package in.ols.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import in.ols.rest.model.ColumnView;
import in.ols.rest.model.SearchParams;
import in.ols.rest.model.Trainer;
import in.ols.rest.model.TrainerDto;
import in.ols.rest.model.TrainerView;
import in.ols.rest.repository.TrainerRepository;
import in.ols.rest.service.IListConfigService;
import in.ols.rest.service.ITrainerService;
import in.ols.rest.util.IdUtil;

@Service
public class TrainerServiceImpl implements ITrainerService {

	private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	IListConfigService listConfigService;

	public TrainerView getTrainerView(SearchParams searchParams) {
		TrainerView trainerView = new TrainerView();
		String userId = searchParams.getId();
		try {
			List<Trainer> filteredTrainerList = getFilteredTrainers(searchParams);
			List<TrainerDto> rowList = getTrainerList(filteredTrainerList);
			List<ColumnView> columnList = listConfigService.getListConfig(userId, "TRAINER");

			trainerView.setColumnList(columnList);
			trainerView.setRowList(rowList);								
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		return trainerView;
	}
	
	private List<Trainer> getFilteredTrainers(SearchParams searchParams) {
		Criteria andCriteria = new Criteria();
		List<Criteria> andExpressions = new ArrayList<Criteria>();
		
		if(searchParams.getFromDate() != null && searchParams.getToDate() != null) {						
			andExpressions.add(Criteria.where("lastModifiedDate").gte(searchParams.getFromDate()).lt(searchParams.getToDate()));
		}
		
		
		if(StringUtils.isEmpty(searchParams.getId()) == false) {
			String userId = searchParams.getUserId();
			if(userId!=null) {
				andExpressions.add(Criteria.where("userId").in(userId));
			}
		}
								
		if(CollectionUtils.isEmpty(andExpressions)) {
			return trainerRepository.findAll();
		} else {
			return mongoTemplate.find(Query.query(andCriteria.andOperator(andExpressions.toArray(new Criteria[andExpressions.size()]))), Trainer.class);
		}		
	}


	@Override
	public List<TrainerDto> getTrainerList(List<Trainer> allTrainers) {
		List<TrainerDto> allTrainerList = new ArrayList<>();
		TrainerDto trainerDto;
		int counter = 0;
		if (allTrainers != null) {
			for (Trainer trainer : allTrainers) {
				trainerDto = new TrainerDto();
				trainerDto.setSerialNumber(++counter);
				trainerDto.setId(trainer.getId());
				allTrainerList.add(trainerDto);
			}
		}

		return allTrainerList;
	}

	@Override
	public Trainer save(TrainerDto trainerDto) throws Exception {
		Trainer newTrainer = new Trainer();
		newTrainer.setId(IdUtil.generateId());				
		newTrainer.setCreateDate(new Date());
		String[] ignoreProperties = {"id"};
		BeanUtils.copyProperties(trainerDto, newTrainer, ignoreProperties);
		trainerRepository.save(newTrainer);
		return newTrainer;
	}

	@Override
	public Trainer udpate(TrainerDto trainerDto) throws Exception {
		Trainer newTrainer = new Trainer();
		
		if (StringUtils.isEmpty(trainerDto.getId()) == false) {
			newTrainer.setId(trainerDto.getId());
		} else {
			throw new Exception("Trainer" +trainerDto.getId()+"is not exist to update");
		}
		
		String[] ignoreProperties = {"id"};
		BeanUtils.copyProperties(trainerDto, newTrainer, ignoreProperties);
		
		newTrainer.setCreateDate(new Date());
		trainerRepository.save(newTrainer);
		return newTrainer;		
	}

	@Override
	public String delete(String trainerId) {
		trainerRepository.delete(trainerId);
		return trainerId;
	}

}
