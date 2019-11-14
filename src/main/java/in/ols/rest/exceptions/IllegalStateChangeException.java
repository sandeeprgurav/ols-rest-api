package in.ols.rest.exceptions;

public class IllegalStateChangeException extends RuntimeException {
   public IllegalStateChangeException() {}

   public IllegalStateChangeException(Throwable cause) {
      super(cause);
   }

   public IllegalStateChangeException(String message) {
      super(message);
   }

   public IllegalStateChangeException(String message, Throwable cause) {
      super(message, cause);
   }
}
