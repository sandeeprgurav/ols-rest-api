package in.ols.rest.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ols.rest.model.Config;
import in.ols.rest.model.Session;
import in.ols.rest.repository.SessionRepository;
import in.ols.rest.service.IConfigService;
import in.ols.rest.service.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService {

   @Autowired
   SessionRepository sessionRepository;
   
   @Autowired
   private IConfigService configService;
   
   @Override
   public boolean isValidSession(String apiToken) {	   
		System.out.println("******************* CHECKING IF API TOKEN "+apiToken+" IS PRESENT IN OUR DB ******************");	   
		if(!configService.getConfigValue(Config.AUTH_ENABLED, Boolean.class)){
		    System.out.println("******************* AUTH DISABLED ******************");
		    return true;
		}      
		Session session = sessionRepository.findByApiToken(apiToken);
		boolean sessionValid = (session != null); 
		if(sessionValid){
		   incrementValidity(session);
		   MDC.put("userType", session.getUserType().name());
		}
		return sessionValid;
   }

   @Override
   public void incrementSessionValidity(String apiToken) {
      Session session = sessionRepository.findByApiToken(apiToken);
      incrementValidity(session);
   }

   private void incrementValidity(Session session) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.SECOND, configService.getConfigValue(Config.SESSION_INCREMENT_TIME_SECONDS, Integer.class));
      session.setValidTill(calendar.getTime());
      sessionRepository.save(session);
   }

	@Override
	public Session startNewSession(String username, String userType) {
		Session currentSession = sessionRepository.findByUsernameAndValidTill(username, new Date());
        
        if(currentSession != null){
           currentSession.setValidTill(new Date());
           currentSession.setComments("Session was already active but received another login attempt!");
           sessionRepository.save(currentSession);
        }
        
		Session newSession = new Session();
        newSession.setApiToken(java.util.UUID.randomUUID().toString());
        newSession.setUsername(username);
        newSession.setLoginTime(new Date());
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, configService.getConfigValue(Config.SESSION_INCREMENT_TIME_SECONDS, Integer.class));
        newSession.setValidTill(calendar.getTime());
        newSession.setValid(true);
        sessionRepository.save(newSession);
        return newSession;
	}
      
}
