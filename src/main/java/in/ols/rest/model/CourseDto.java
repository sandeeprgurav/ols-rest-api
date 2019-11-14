package in.ols.rest.model;

import in.ols.rest.model.BaseModel.Status;

public class CourseDto implements Comparable<CourseDto>{
	
	private String id;
	private int serialNumber;
	private String userId;
	private String name;
	private String description;
	private String category;
	private Status status;
	private String enteredBy;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public int compareTo(CourseDto arg0) {
		return 0;
	}	

}

