package in.ols.rest.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.ObjectError;

public class ArgumentException extends Exception {

   private Integer errorCode;
   private List<ArgumentException.FieldError> fieldErrors;

   public ArgumentException() {
      super();
   }

   public ArgumentException(String message) {
      super(message);
   }

   public ArgumentException(String message, Throwable rootCause) {
      super(message, rootCause);
   }

   public ArgumentException(Throwable rootCause) {
      super(rootCause);
   }

   public ArgumentException(List<ObjectError> errors) {
      fieldErrors = new ArrayList<ArgumentException.FieldError>();
      for (ObjectError error : errors) {
         if (error instanceof org.springframework.validation.FieldError) {
            FieldError fieldError = new FieldError(((org.springframework.validation.FieldError) error).getField(),
                  error.getDefaultMessage(), ((org.springframework.validation.FieldError) error).getRejectedValue());
            fieldErrors.add(fieldError);
         } else {
            FieldError fieldError = new FieldError(error.getCode(), error.getDefaultMessage());
            fieldErrors.add(fieldError);
         }
      }
   }

   public ArgumentException(String message, String field, String value) {
      FieldError error = new FieldError(field, message, value);
      fieldErrors = new ArrayList<>();
      fieldErrors.add(error);
   }

   @Override
   public String toString() {
      String s = super.toString() + getMessage();
      if (fieldErrors != null) {
         for (FieldError fieldError : fieldErrors) {
            s += " | " + fieldError;
         }
      }
      return s;
   }

   @JsonInclude(value = JsonInclude.Include.NON_NULL)
   public static final class FieldError implements Serializable {
      // Erroneous Field Name
      private String field;
      // Value of error field
      private Object fieldValue;
      // Error Message
      private String message;

      public FieldError(String field, String message) {
         this(field, message, null);
      }

      public FieldError(String field, String message, Object fieldValue) {
         this.field = field;
         this.message = message;
         this.fieldValue = fieldValue;
      }

      public String getField() {
         return field;
      }

      public Object getFieldValue() { return fieldValue; }

      public String getMessage() {
         return message;
      }

      @Override
      public String toString() {
         return "Field=" + field + " Message=" + message + " Value=" + fieldValue;
      }

   }

   public void addFieldError(String parameterName, String message) {
      getFieldErrors().add(new FieldError(parameterName, message));
   }

   public void addFieldError(String parameterName, String message, Object fieldValue) {
      getFieldErrors().add(new FieldError(parameterName, message, fieldValue));
   }

   public Integer getErrorCode() {
      return errorCode;
   }

   public void setErrorCode(Integer errorCode) {
      this.errorCode = errorCode;
   }

   public List<ArgumentException.FieldError> getFieldErrors() {
      if (null == fieldErrors) {
         fieldErrors = new ArrayList<>();
      }
      return fieldErrors;
   }

   public void setFieldErrors(List<ArgumentException.FieldError> fieldErrors) {
      this.fieldErrors = fieldErrors;
   }
}
