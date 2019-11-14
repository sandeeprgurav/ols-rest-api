package in.ols.rest.service;

import java.util.List;

import in.ols.rest.model.Course;
import in.ols.rest.model.CourseDto;

public interface ICourseService {

	List<CourseDto> getCourseList(List<Course> allCourses);

	Course save(CourseDto courseDto) throws Exception;
	
	Course udpate(CourseDto courseDto) throws Exception ;

	String delete(String courseId) throws Exception;
		
}
