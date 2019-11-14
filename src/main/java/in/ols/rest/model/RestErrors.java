package in.ols.rest.model;

import java.util.ArrayList;
import java.util.List;


public class RestErrors {
   private List<RestErrorData> errors;

   public RestErrors() {
      this(null);
   }

   public RestErrors(List<RestErrorData> errors) {
      this.errors = errors;
   }

   public List<RestErrorData> getErrors() {
      if (errors == null) {
         errors = new ArrayList<RestErrorData>();
      }
      return errors;
   }

   public void setErrors(List<RestErrorData> errors) {
      this.errors = errors;
   }

   public void addError(RestErrorData error) {
      if (error != null) {
         getErrors().add(error);
      }
   }

   @Override
   public String toString() {
      return "RestErrors [errors=" + errors + "]";
   }
}
