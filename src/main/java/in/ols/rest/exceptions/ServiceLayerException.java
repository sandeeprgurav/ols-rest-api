package in.ols.rest.exceptions;

import org.springframework.http.HttpStatus;


public class ServiceLayerException extends RuntimeException {

   private Integer statusCode;

   public ServiceLayerException(String message) {
      super(message);
   }

   public ServiceLayerException(Throwable cause) {
      super(cause);
   }

   public ServiceLayerException(String message, Throwable cause) {
      super(message, cause);
   }

   public ServiceLayerException(HttpStatus status) {
      this.statusCode = status.value();
   }

   public ServiceLayerException(Integer statusCode) {
      this.statusCode = statusCode;
   }

   public ServiceLayerException(Throwable cause, HttpStatus status) {
      super(cause);
      this.statusCode = status.value();
   }

   public ServiceLayerException(Throwable cause, Integer statusCode) {
      super(cause);
      this.statusCode = statusCode;
   }

   public ServiceLayerException(String message, HttpStatus status) {
      super(message);
      this.statusCode = status.value();
   }

   public ServiceLayerException(String message, Integer statusCode) {
      super(message);
      this.statusCode = statusCode;
   }

   public ServiceLayerException(String message, Throwable cause, HttpStatus status) {
      super(message, cause);
      this.statusCode = status.value();
   }

   public ServiceLayerException(String message, Throwable cause, Integer statusCode) {
      super(message, cause);
      this.statusCode = statusCode;
   }

   public ServiceLayerException(String message, Throwable cause, boolean enableSuppression,
         boolean writableStackTrace, Integer statusCode) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.statusCode = statusCode;
   }

   public ServiceLayerException(String message, Throwable cause, boolean enableSuppression,
         boolean writableStackTrace, HttpStatus status) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.statusCode = status.value();
   }

   public Integer getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(Integer statusCode) {
      this.statusCode = statusCode;
   }
}
