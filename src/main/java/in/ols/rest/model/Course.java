package in.ols.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
public class Course extends BaseModel {
	
	@Id
	private String id;
	private String userId;
	private String name;
	private String description;
	private boolean activityFlag;
	private String category;
		
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public boolean isActivityFlag() {
		return activityFlag;
	}
	public void setActivityFlag(boolean activityFlag) {
		this.activityFlag = activityFlag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
			
}
