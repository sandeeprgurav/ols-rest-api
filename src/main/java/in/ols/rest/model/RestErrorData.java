package in.ols.rest.model;

public class RestErrorData {
   public enum ErrorType {INPUT_VALIDATION, AUTHENTICATION, SERVICE, OTHER}

   private ErrorType errorType;
   private Integer errorCode;
   private String url;
   private Object errorDetails;

   public RestErrorData() {
      this(null);
   }

   public RestErrorData(ErrorType errorType) {
      this(errorType, null);
   }

   public RestErrorData(ErrorType errorType, Integer errorCode) {
      this(errorType, errorCode, null);
   }

   public RestErrorData(ErrorType errorType, Integer errorCode, Object errorDetails) {
      this.errorType = errorType;
      this.errorCode = errorCode;
      this.errorDetails = errorDetails;
   }

   public RestErrorData(ErrorType errorType, Integer errorCode, String url, Object errorDetails) {
      this.errorType = errorType;
      this.errorCode = errorCode;
      this.url = url;
      this.errorDetails = errorDetails;
   }

   public Integer getErrorCode() {
      return errorCode;
   }

   public void setErrorCode(Integer errorCode) {
      this.errorCode = errorCode;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public Object getErrorDetails() {
      return errorDetails;
   }

   public void setErrorDetails(Object errorDetails) {
      this.errorDetails = errorDetails;
   }

   public ErrorType getErrorType() {
      return errorType;
   }

   public void setErrorType(ErrorType errorType) {
      this.errorType = errorType;
   }

   @Override
   public String toString() {
      return "RestErrorData [errorType=" + errorType + ", errorCode=" + errorCode + ", url=" + url + ", errorDetails=" + errorDetails + "]";
   }
}
