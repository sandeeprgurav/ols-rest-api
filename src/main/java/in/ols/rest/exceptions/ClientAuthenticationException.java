package in.ols.rest.exceptions;

public class ClientAuthenticationException extends Exception {
   public ClientAuthenticationException() {
      super();
   }

   public ClientAuthenticationException(String message) {
      super(message);
   }

   public ClientAuthenticationException(String message, Throwable cause) {
      super(message, cause);
   }

   public ClientAuthenticationException(Throwable cause) {
      super(cause);
   }
}
