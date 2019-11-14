package in.ols.rest.controllers;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ols.rest.model.LoginResponse;
import in.ols.rest.model.LogoutResponse;
import in.ols.rest.model.Session;
import in.ols.rest.model.User;
import in.ols.rest.repository.SessionRepository;
import in.ols.rest.repository.UserRepository;
import in.ols.rest.service.ISessionService;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

   @Autowired
   private UserRepository repository;
   
   @Autowired
   private SessionRepository sessionRepository;
   
   @Autowired
   private ISessionService sessionService;

   @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
   public LoginResponse login(@RequestParam String username, @RequestParam String password) {
      User user = repository.findByUsername(username);
      String userType = user.getUserType();
      LoginResponse loginResponse = new LoginResponse();
      loginResponse.setSuccess(false);
      if(user != null && user.getPassword().equalsIgnoreCase(password) && user.isActive()){
    	  // User authenticated and is active

    	  Session newSession = sessionService.startNewSession(username, userType);
    	  loginResponse.setApiToken(newSession.getApiToken());
    	  loginResponse.setSuccess(true);
    	  loginResponse.setUserType(userType);
    	  loginResponse.setUsername(newSession.getUsername());
    	  return loginResponse;
      }else{
         loginResponse.setError("User not found or incorrect credentials!!");
      }
      return loginResponse;
   }
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
   public LogoutResponse logout(@RequestParam String apiToken) {
         Session currentSession = sessionRepository.findByApiToken(apiToken);
         currentSession.setValidTill(new Date());
         currentSession.setValid(false);
         sessionRepository.save(currentSession);
         return new LogoutResponse("", true);
      }
   
   
   
}