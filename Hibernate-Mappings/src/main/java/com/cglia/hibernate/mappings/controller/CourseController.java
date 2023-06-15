package com.cglia.hibernate.mappings.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cglia.hibernate.mappings.entity.Course;
import com.cglia.hibernate.mappings.entity.Review;
import com.cglia.hibernate.mappings.entity.Student;
import com.cglia.hibernate.mappings.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	    private CourseService courseService;

	    public CourseController(CourseService courseService) {
	        this.courseService = courseService;
	    }

	    @GetMapping("/v1.0")
	    public List<Course> getAllCourses() {
	        return courseService.findAll();
	    }

	    @GetMapping("/v1.0/{id}")
	    public Course getCourseById(@PathVariable int id) {
	        return courseService.findById(id);
	    }

	    @PostMapping("/v1.0")
	    public void addCourse(@RequestBody Course course) {
	        courseService.save(course);
	    }

	    @PutMapping("/v1.0/{id}")
	    public void updateCourse(@RequestBody Course course, @PathVariable int id) {
	        courseService.update(course, id);
	    }

	    @DeleteMapping("/v1.0/{id}")
	    public void deleteCourse(@PathVariable int id) {
	        courseService.deleteById(id);
	    }
	    
	    @GetMapping("/v1.0/reviews/{id}")
	    public List<Review> getAllCourseReviews(@PathVariable int id){
	    	return courseService.getAllCourseReviews(id);
	    }
	    
	    @GetMapping("/v1.0/students/{id}")
	    public List<Student> getAllStudentForCourse(@PathVariable int id){
	    	return courseService.getAllStudentsForCourse(id);
	    }
	}

