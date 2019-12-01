package in.ols.rest.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseModel {

	public enum Status {
	      CREATED,
	      MODIFIED,
	      DELETED,
	      ASSIGNED,
	      CANCELLED,
	      INPROGRESS,
	      COMPLETED	      
	}
	
	private String disabled;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastModifiedDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;
	
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception arg1) {
			return "";
		}
	}
}
