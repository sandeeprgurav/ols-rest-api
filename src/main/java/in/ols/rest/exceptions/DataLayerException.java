package in.ols.rest.exceptions;

public class DataLayerException extends RuntimeException {
   public DataLayerException() {
   }

   public DataLayerException(Throwable cause) {
      super(cause);
   }

   public DataLayerException(String message) {
      super(message);
   }

   public DataLayerException(String message, Throwable cause) {
      super(message, cause);
   }

   public DataLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
