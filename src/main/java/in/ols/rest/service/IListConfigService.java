package in.ols.rest.service;

import java.util.List;

import in.ols.rest.model.ColumnView;
import in.ols.rest.model.ListConfig;

public interface IListConfigService {

	 public List<ColumnView> getListConfig(String userId, String listType);
	 public List<ColumnView> getListConfig(String userId, String listType, String listSubType);

	public void addOrUpdateListConfig(ListConfig listConfig);
   
}
