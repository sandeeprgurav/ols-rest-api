package in.ols.rest.aop;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAop {

   private static final Logger logger = LoggerFactory.getLogger(LoggingAop.class);

   @Autowired
   private HttpServletRequest request;

   @Autowired
   private HttpServletResponse response;

   @Pointcut("execution(public * in.ols.rest.controllers..*.*(..))")
   private void controllerPC() {
   }


   @Before("controllerPC()")
   public void doBeforeControllerCall(JoinPoint jp) throws IOException {
      logger.info("@Before: " + getShortName(jp.getSignature()) + ", args: " + argsToString(jp.getArgs()));
   }

   @AfterReturning("controllerPC()")
   public void doAfterReturningControllerCall(JoinPoint jp) {
      logger.info("@AfterReturning normally: " + getShortName(jp.getSignature()));
   }

   static String getShortName(Signature signature) {
      return signature.getDeclaringType().getSimpleName() + "." + signature.getName();
   }

   static String argsToString(Object[] args) {
      StringBuilder stringBuilder = new StringBuilder("{");
      for (Object obj : args) {
         stringBuilder.append(obj + ", ");
      }
      int lastComma = stringBuilder.lastIndexOf(", ");
      if (lastComma != -1) {
         stringBuilder.delete(lastComma, lastComma + 2);
      }
      stringBuilder.append("}");
      return stringBuilder.toString();
   }
}
