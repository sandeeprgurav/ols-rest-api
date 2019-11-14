package in.ols.rest.repository;


import in.ols.rest.model.Config;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Jugesh Bansal
 *
 */
@Repository
public interface ConfigRepository extends MongoRepository<Config, String>{

   public Config findByProperty(String propString);
   
}
