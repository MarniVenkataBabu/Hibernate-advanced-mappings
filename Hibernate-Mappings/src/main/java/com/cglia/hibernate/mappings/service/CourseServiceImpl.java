package com.cglia.hibernate.mappings.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.cglia.hibernate.mappings.entity.Course;
import com.cglia.hibernate.mappings.entity.Review;
import com.cglia.hibernate.mappings.entity.Student;
import com.cglia.hibernate.mappings.exceptionhandling.GlobalNotFoundException;
import com.cglia.hibernate.mappings.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course findById(int id) {
		Optional<Course> result = courseRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new GlobalNotFoundException("Course not found with id: " + id);
		}
	}

	@Override
	public void save(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void update(Course course, int id) {
		Optional<Course> result = courseRepository.findById(id);
		if (result.isPresent()) {
			Course existingCourse = result.get();
			existingCourse.setTitle(course.getTitle());
			existingCourse.setInstructor(course.getInstructor());
			existingCourse.setDurationWeek(course.getDurationWeek());
			existingCourse.setReviews(course.getReviews());
			existingCourse.setStudents(course.getStudents());
			courseRepository.save(existingCourse);
		} else {
			throw new GlobalNotFoundException("Course not found with id: " + id);
		}
	}

	@Override
	public void deleteById(int id) {
		courseRepository.deleteById(id);
	}

	@Override
	public List<Review> getAllCourseReviews(int id) {
		Optional<Course> result = courseRepository.findById(id);
		Course tempCourse = null;
		if(result.isPresent()) {
			tempCourse = result.get();
		}else {
			throw new GlobalNotFoundException("Course not found with given id :"+id);
		}
		return tempCourse.getReviews();
	}

	@Override
	public List<Student> getAllStudentsForCourse(int id) {
		Optional<Course> result = courseRepository.findById(id);
		Course tempCourse = null;
		if(result.isPresent()) {
			tempCourse = result.get();
		}else {
			throw new GlobalNotFoundException("Course not found with given id :"+id);
		}
		return tempCourse.getStudents();
	}
}
