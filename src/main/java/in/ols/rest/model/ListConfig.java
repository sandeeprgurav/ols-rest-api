package in.ols.rest.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "listConfig")
public class ListConfig extends BaseModel{
	
	private String id;
	private String userId;
	private String listType;
	private String listSubType;
	
	private List<ColumnView> columnConfig;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public List<ColumnView> getColumnConfig() {
		return columnConfig;
	}
	public void setColumnConfig(List<ColumnView> columnConfig) {
		this.columnConfig = columnConfig;
	}
	public String getListSubType() {
		return listSubType;
	}
	public void setListSubType(String listSubType) {
		this.listSubType = listSubType;
	}	    
}
