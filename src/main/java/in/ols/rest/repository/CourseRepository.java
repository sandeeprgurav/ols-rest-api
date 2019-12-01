package in.ols.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.ols.rest.model.Course;

public interface CourseRepository extends MongoRepository<Course, String>{
}
