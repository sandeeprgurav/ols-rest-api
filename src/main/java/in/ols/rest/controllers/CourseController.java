package in.ols.rest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import in.ols.rest.model.Course;
import in.ols.rest.model.CourseDto;
import in.ols.rest.repository.CourseRepository;
import in.ols.rest.service.ICourseService;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/course")
public class CourseController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ICourseService courseService;
		
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Course> list() {
		return courseRepository.findAll();
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	public Course get(@RequestParam String courseId) {
		return courseRepository.findOne(courseId);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "CourseController.save", notes = "Creates a Course entry in courses collection.")
	public Course save(@RequestBody CourseDto courseDto) throws Exception {
		courseDto.setEnteredBy(MDC.get("username"));
		courseDto.setStatus(Course.Status.CREATED);
		logger.info("CourseController.save method called");
		return courseService.save(courseDto);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "CourseController.update", notes = "Update a Course entry in courses collection.")
	public Course update(@RequestBody CourseDto courseDto) throws Exception {
		courseDto.setEnteredBy(MDC.get("username"));
		courseDto.setStatus(Course.Status.MODIFIED);
		logger.info("CourseController.update method called");		
		return courseService.udpate(courseDto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = { "application/json" }, produces = "application/json")
	@ApiOperation(value = "CourseController.delete", notes = "Delete a Course entry in courses collection.")
	public String delete(@RequestParam String courseId) throws Exception {		
		logger.info("CourseController.delete method called");
		return courseService.delete(courseId);
	}	
		
}
