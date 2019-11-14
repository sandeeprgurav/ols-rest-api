package in.ols.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import in.ols.rest.model.ColumnView;
import in.ols.rest.model.Course;
import in.ols.rest.model.CourseDto;
import in.ols.rest.model.CourseView;
import in.ols.rest.model.SearchParams;
import in.ols.rest.model.Trainer;
import in.ols.rest.repository.CourseRepository;
import in.ols.rest.service.ICourseService;
import in.ols.rest.service.IListConfigService;
import in.ols.rest.util.IdUtil;

@Service
public class CourseServiceImpl implements ICourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	IListConfigService listConfigService;

	public CourseView getCourseView(SearchParams searchParams) {
		CourseView CourseView = new CourseView();
		String userId = searchParams.getId();
		try {
			List<Course> filteredCourseList = getFilteredCourses(searchParams);
			List<CourseDto> rowList = getCourseList(filteredCourseList);
			List<ColumnView> columnList = listConfigService.getListConfig(userId, "COURSE");

			CourseView.setColumnList(columnList);
			CourseView.setRowList(rowList);								
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		return CourseView;
	}
	
	private List<Course> getFilteredCourses(SearchParams searchParams) {
		Criteria andCriteria = new Criteria();
		List<Criteria> andExpressions = new ArrayList<Criteria>();
		
		if(searchParams.getFromDate() != null && searchParams.getToDate() != null) {						
			andExpressions.add(Criteria.where("lastModifiedDate").gte(searchParams.getFromDate()).lt(searchParams.getToDate()));
		}
		
		
		if(StringUtils.isEmpty(searchParams.getId()) == false) {
			String userId = searchParams.getUserId();
			if(userId!=null) {
				andExpressions.add(Criteria.where("userId").in(userId));
			}
		}
								
		if(CollectionUtils.isEmpty(andExpressions)) {
			return courseRepository.findAll();
		} else {
			return mongoTemplate.find(Query.query(andCriteria.andOperator(andExpressions.toArray(new Criteria[andExpressions.size()]))), Course.class);
		}		
	}


	@Override
	public List<CourseDto> getCourseList(List<Course> allCourses) {
		List<CourseDto> allCourseList = new ArrayList<>();
		CourseDto CourseDto;
		int counter = 0;
		if (allCourses != null) {
			for (Course Course : allCourses) {
				CourseDto = new CourseDto();
				CourseDto.setSerialNumber(++counter);
				CourseDto.setId(Course.getId());
				allCourseList.add(CourseDto);
			}
		}

		return allCourseList;
	}

	@Override
	public Course save(CourseDto courseDto) throws Exception {
		Course newCourse = new Course();
		if (newCourse != null) {			
			newCourse.setId(IdUtil.generateId());			
		}
		newCourse.setCreateDate(new Date());
		String[] ignoreProperties = {"id"};
		BeanUtils.copyProperties(courseDto, newCourse,ignoreProperties);
		
		courseRepository.save(newCourse);
		return newCourse;
	}

	@Override
	public Course udpate(CourseDto courseDto) throws Exception {
		Course newCourse = new Course();
		if (StringUtils.isEmpty(courseDto.getId()) == false) {
			newCourse.setId(courseDto.getId());
		} else {
			throw new Exception("Course" +courseDto.getId()+"is not exist to update");
		}
		String[] ignoreProperties = {"id"};
		BeanUtils.copyProperties(courseDto, newCourse, ignoreProperties);
		
		newCourse.setCreateDate(new Date());
		courseRepository.save(newCourse);
		return newCourse;	
	}

	@Override
	public String delete(String courseId) throws Exception {
		courseRepository.delete(courseId);
		return courseId;
	}

}
