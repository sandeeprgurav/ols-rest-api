package in.ols.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import in.ols.rest.model.ColumnView;
import in.ols.rest.model.ListConfig;
import in.ols.rest.repository.ListConfigRepository;
import in.ols.rest.service.IListConfigService;

@Service
public class ListConfigServiceImpl implements IListConfigService {
   
	private static final String USER_ID_ALL = "ALL";
	private static final String DEFAULT_LIST_SUB_TYPE= "ALL";
   
   @Autowired
   ListConfigRepository listConfigRepository;

	@Override
	public List<ColumnView> getListConfig(String userId, String listType) {
		ListConfig listConfig = listConfigRepository.findByUserIdAndListType(userId, listType);
		List<ColumnView> columnViewList = new ArrayList<>();
		if(ObjectUtils.isEmpty(listConfig)) {
			listConfig = listConfigRepository.findByUserIdAndListType(USER_ID_ALL, listType);
		}
		
		if(ObjectUtils.isEmpty(listConfig) == false) {
			columnViewList = listConfig.getColumnConfig();
		}
		
		return columnViewList;
	}

	@Override
	public void addOrUpdateListConfig(ListConfig listConfig) {
		String userId = listConfig.getUserId();
		String listType = listConfig.getListType();
		String listSubType = listConfig.getListSubType();
		if(listSubType==null || listSubType.length()==0 || listSubType.equals("undefined")) {
			listSubType = DEFAULT_LIST_SUB_TYPE;
		}
		ListConfig originalListConfig = listConfigRepository.findByUserIdAndListTypeAndListSubType(USER_ID_ALL, listType, listSubType);
				
		ListConfig userListConfig = listConfigRepository.findByUserIdAndListTypeAndListSubType(userId, listType, listSubType);
		try {
			if(ObjectUtils.isEmpty(userListConfig)) { //Add new ListConfig for userId
				userListConfig = originalListConfig;
				userListConfig.setId(null);
				userListConfig.setUserId(userId);
				userListConfig.setColumnConfig(populateModifiedColumnList(listConfig.getColumnConfig(), originalListConfig.getColumnConfig()));
				listConfigRepository.save(userListConfig);
			} else { //Update Existing ListConfig
				userListConfig.setColumnConfig(populateModifiedColumnList(listConfig.getColumnConfig(), originalListConfig.getColumnConfig()));
				listConfigRepository.save(userListConfig);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
	}

	private List<ColumnView> populateModifiedColumnList(List<ColumnView> modifiedColumnViewList, List<ColumnView> originalColumnViewList) {
		
		for(ColumnView originalColumnView : originalColumnViewList) {
			boolean columnViewPresent= false;
			for(ColumnView modifiedColumnView : modifiedColumnViewList) {
				if(modifiedColumnView.getKey().equals(originalColumnView.getKey())) {
					columnViewPresent = true;
				}
			}
			
			if(columnViewPresent == false) {
				originalColumnView.setVisible(false);
			}
			
		}
		
		return originalColumnViewList;
	}

	@Override
	public List<ColumnView> getListConfig(String userId, String listType, String listSubType) {
		ListConfig listConfig = listConfigRepository.findByUserIdAndListTypeAndListSubType(userId, listType, listSubType);
		List<ColumnView> columnViewList = new ArrayList<>();
		
		if(ObjectUtils.isEmpty(listConfig)) {
			listConfig = listConfigRepository.findByUserIdAndListTypeAndListSubType(USER_ID_ALL, listType, listSubType);
		}
		
		if(ObjectUtils.isEmpty(listConfig) == false) {
			columnViewList = listConfig.getColumnConfig();
		}
		
		return columnViewList;
	}
}
