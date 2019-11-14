package in.ols.rest.controllers;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ols.rest.model.Trainer;
import in.ols.rest.model.TrainerDto;
import in.ols.rest.repository.TrainerRepository;
import in.ols.rest.service.ITrainerService;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

	@Autowired
	private TrainerRepository trainerRepository;
	
	@Autowired
	private ITrainerService trainerService;
		
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Trainer> list() {
		return trainerRepository.findAll();
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	public Trainer get(@RequestParam String trainerId) {
		return trainerRepository.findOne(trainerId);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "TrainerController.save", notes = "Creates a Trainer entry in trainers collection.")
	public Trainer save(@RequestBody TrainerDto trainerDto) throws Exception {
		trainerDto.setEnteredBy(MDC.get("username"));
		trainerDto.setStatus(Trainer.Status.CREATED);
		logger.info("TrainerController.save method called");
		return trainerService.save(trainerDto);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "TrainerController.update", notes = "Update a Trainer entry in trainers collection.")
	public Trainer update(@RequestBody TrainerDto trainerDto) throws Exception {
		trainerDto.setEnteredBy(MDC.get("username"));
		trainerDto.setStatus(Trainer.Status.MODIFIED);
		logger.info("TrainerController.update method called");		
		return trainerService.udpate(trainerDto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "TrainerController.delete", notes = "Delete a Trainer entry in trainers collection.")
	public String delete(@RequestParam String trainerId) throws Exception {		
		logger.info("TrainerController.delete method called");
		return trainerService.delete(trainerId);
	}
	
	
	
	
		
}
