package in.ols.rest.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import in.ols.rest.model.User.UserType;

@Document(collection = "login_session")
public class Session {
   
   @Id
   private String id;
   @Indexed
   private String apiToken;
   private String username;
   private Date loginTime;
   private Date validTill;
   private String comments;
   private boolean isValid;
   private UserType userType;
   
   public String getApiToken() {
      return apiToken;
   }
   public void setApiToken(String apiToken) {
      this.apiToken = apiToken;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public Date getLoginTime() {
      return loginTime;
   }
   public void setLoginTime(Date loginTime) {
      this.loginTime = loginTime;
   }
   public Date getValidTill() {
      return validTill;
   }
   public void setValidTill(Date validTill) {
      this.validTill = validTill;
   }
   
   public String getComments() {
      return comments;
   }
   public void setComments(String comments) {
      this.comments = comments;
   }
   public boolean isValid() {
      return isValid;
   }
   public void setValid(boolean isValid) {
      this.isValid = isValid;
   }
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", apiToken=" + apiToken + ", username=" + username + ", loginTime=" + loginTime
				+ ", validTill=" + validTill + ", comments=" + comments + ", isValid=" + isValid + ", userType="
				+ userType + "]";
	}
	
}
