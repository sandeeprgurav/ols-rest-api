package in.ols.rest.service;

import java.util.List;

import in.ols.rest.model.Trainer;
import in.ols.rest.model.TrainerDto;

public interface ITrainerService {
	

	List<TrainerDto> getTrainerList(List<Trainer> allTrainers);

	Trainer save(TrainerDto bookingDto) throws Exception;

	Trainer udpate(TrainerDto trainerDto) throws Exception ;

	String delete(String trainerId) throws Exception;
		
}
