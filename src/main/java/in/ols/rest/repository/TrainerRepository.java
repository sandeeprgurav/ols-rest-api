package in.ols.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.ols.rest.model.Trainer;

public interface TrainerRepository extends MongoRepository<Trainer, String>{   
   
}

