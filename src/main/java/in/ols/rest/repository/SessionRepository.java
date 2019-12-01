package in.ols.rest.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.ols.rest.model.Session;

@Repository
public interface SessionRepository extends MongoRepository<Session, String>{
   @Query("{'validTill':{'$gt':?1 }}") 
   public Session findByUsernameAndValidTill(String username, Date validTill);
   
   @Query("{'validTill':{'$gt':?1 }}") 
   public Session findByApiTokenAndValidTill(String apiToken, Date validTill);
   
   public Session findByApiToken(String apiToken);
   
}
