package in.ols.rest.repository;

import in.ols.rest.model.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	@Query("{'username':{'$eq':?0}}")
	public User findByUsername(String username);
	@Query("{'phoneNumber':{'$eq':?0}}")
   public User findByPhoneNumber(String phoneNumber);
	public List<User> findByUserType(String userType);
}
