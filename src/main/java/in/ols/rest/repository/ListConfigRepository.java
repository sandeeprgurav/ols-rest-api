package in.ols.rest.repository;

import in.ols.rest.model.ListConfig;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListConfigRepository extends MongoRepository<ListConfig, String>{

   public ListConfig findByUserIdAndListType(String userId, String listType);
   public ListConfig findByUserIdAndListTypeAndListSubType(String userId, String listType, String listSubType);
   
}
