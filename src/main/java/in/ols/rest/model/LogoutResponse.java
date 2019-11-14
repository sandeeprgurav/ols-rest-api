package in.ols.rest.model;

/**
 * 
 * @author Jugesh Bansal
 *
 */
public class LogoutResponse {

   private String error;
   private boolean success;
   
   public LogoutResponse(String error, boolean success) {
      super();
      this.error = error;
      this.success = success;
   }
   public String getError() {
      return error;
   }
   public void setError(String error) {
      this.error = error;
   }
   public boolean isSuccess() {
      return success;
   }
   public void setSuccess(boolean success) {
      this.success = success;
   }
   @Override
   public String toString() {
      return "LogoutResponse [error=" + error + ", success=" + success + "]";
   }
}
