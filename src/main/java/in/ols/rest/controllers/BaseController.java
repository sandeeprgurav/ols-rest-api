package in.ols.rest.controllers;


import static in.ols.rest.model.RestErrorData.ErrorType.INPUT_VALIDATION;
import static in.ols.rest.model.RestErrorData.ErrorType.OTHER;
import static in.ols.rest.model.RestErrorData.ErrorType.SERVICE;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import in.ols.rest.exceptions.ArgumentException;
import in.ols.rest.exceptions.DataLayerException;
import in.ols.rest.exceptions.ServiceLayerException;
import in.ols.rest.model.RestErrorData;
import in.ols.rest.model.RestErrors;


public class BaseController {

   private Logger log = LoggerFactory.getLogger(this.getClass());
   
   @ExceptionHandler({ServiceLayerException.class})
   @ResponseBody
   public RestErrors handleServiceLayerException(ServiceLayerException exception, HttpServletResponse response) {
      int statusCode = exception.getStatusCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : exception.getStatusCode();

      return handleServiceOrClientException(exception, response, overrideAuthStatusCodes(statusCode));
   }

  private Integer overrideAuthStatusCodes(Integer statusCode) {
      List<Integer> authStatusCodes = Arrays.asList(HttpStatus.UNAUTHORIZED.value(), HttpStatus.FORBIDDEN.value());

      if (authStatusCodes.contains(statusCode)) {
         statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
      }

      return statusCode;
   }

   @ExceptionHandler({ArgumentException.class})
   @ResponseBody
   public RestErrors handleArgumentException(ArgumentException argumentException, HttpServletResponse response) {
      RestErrors errors = new RestErrors();
      List<ArgumentException.FieldError> fieldErrors = argumentException.getFieldErrors();

      if (fieldErrors != null) {
         for (ArgumentException.FieldError fieldError : fieldErrors) {
            RestErrorData error = new RestErrorData(INPUT_VALIDATION, argumentException.getErrorCode(), fieldError);
            errors.addError(error);
         }
      }
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      return errors;
   }

   @ExceptionHandler({Exception.class, DataLayerException.class})
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ResponseBody
   public RestErrors handleAnyOtherException(Exception exception) {
      RestErrors errors = new RestErrors();
      RestErrorData errorData = new RestErrorData(OTHER, HttpStatus.INTERNAL_SERVER_ERROR.value(),
            (exception.getCause() == null) ? exception.getMessage() : exception.getCause().getMessage());

      errors.addError(errorData);
      log.error("Error occurred", exception);
      return errors;
   }

   @ExceptionHandler({UnsupportedOperationException.class})
   @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
   @ResponseBody
   public RestErrors handleUnsupportedOperationException(UnsupportedOperationException exception, HttpServletResponse response) {
      return handleServiceOrClientException(exception, response, HttpStatus.NOT_IMPLEMENTED.value());
   }

   @ExceptionHandler({HttpMessageNotReadableException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   public RestErrors handleJsonMappingException(HttpMessageNotReadableException exception, HttpServletResponse response) {
      return handleServiceOrClientException(exception, response, HttpStatus.BAD_REQUEST.value());
   }

   @ExceptionHandler({MissingServletRequestParameterException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   public RestErrors handleMissingRequestParameter(MissingServletRequestParameterException exception, HttpServletResponse response) {
      return handleInputException(exception, response, HttpStatus.BAD_REQUEST.value());
   }

   @ExceptionHandler({IllegalArgumentException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   public RestErrors handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) {
      return handleServiceOrClientException(exception, response, HttpStatus.BAD_REQUEST.value());
   }

   private RestErrors handleServiceOrClientException(Exception exception, HttpServletResponse response, int statusCode) {
      return handleException(SERVICE, exception, response, statusCode);
   }

   private RestErrors handleInputException(Exception exception, HttpServletResponse response, int statusCode) {
      return handleException(INPUT_VALIDATION, exception, response, statusCode);
   }

   private RestErrors handleException(RestErrorData.ErrorType errorType, Exception exception, HttpServletResponse response, int statusCode) {
      response.setStatus(statusCode);
      RestErrors errors = new RestErrors();
      errors.addError(new RestErrorData(errorType, statusCode, null, exception.getMessage()));
      return errors;
   }


   @ExceptionHandler({ConstraintViolationException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   public RestErrors handleConstraintViolationException(ConstraintViolationException exception) {
      RestErrors errors = new RestErrors();
      exception.getConstraintViolations().forEach(cv -> {
         RestErrorData errorData = new RestErrorData(INPUT_VALIDATION, HttpStatus.BAD_REQUEST.value(), cv.getMessage());
         errors.addError(errorData);
         cv.getMessage();
      });

      return errors;
   }

}
