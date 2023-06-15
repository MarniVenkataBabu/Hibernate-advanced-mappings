package com.cglia.hibernate.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cglia.hibernate.mappings.entity.Course;
import com.cglia.hibernate.mappings.entity.Instructor;
import com.cglia.hibernate.mappings.entity.InstructorDetail;
import com.cglia.hibernate.mappings.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

	    private InstructorService instructorService;

	    @Autowired
	    public InstructorController(InstructorService instructorService) {
	        this.instructorService = instructorService;
	    }

	    @GetMapping("/v1.0")
	    public List<Instructor> getAllInstructors() {
	        return instructorService.findAll();
	    }

	    @GetMapping("/v1.0/{id}")
	    public Instructor getInstructorById(@PathVariable int id) {
	        return instructorService.findById(id);
	    }

	    @PostMapping("/v1.0")
	    public ResponseEntity<String> createInstructor(@RequestBody CreateInstructorRequest request) {
	        try {
	        	instructorService.save(request.getInstructor(), request.getInstructorDetail());
	            return ResponseEntity.ok("Instructor and Instructor details saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create instructor: " + e.getMessage());
	        }
	    }

	    @PutMapping("/v1.0/{id}")
	    public void updateInstructor(@PathVariable int id, @RequestBody Instructor instructorDTO) {
	        Instructor instructor = new Instructor(instructorDTO.getFirstName(), instructorDTO.getLastName(), instructorDTO.getEmail());
	        instructorService.update(instructor, id);
	    }

	    @DeleteMapping("/v1.0/{id}")
	    public void deleteInstructor(@PathVariable int id) {
	        instructorService.deleteById(id);
	    }

	    @PutMapping("/v1.0/update-instructor-detail/{id}")
	    public void updateInstructorAndDetail(@PathVariable int id, @RequestBody Instructor instructorDTO, InstructorDetail instructorDetailDTO) {
	        Instructor instructor = new Instructor(instructorDTO.getFirstName(), instructorDTO.getLastName(), instructorDTO.getEmail());
	        InstructorDetail instructorDetail = new InstructorDetail(instructorDetailDTO.getYoutubeChannel(), instructorDetailDTO.getHobby(), instructorDetailDTO.getExperienceInYears());
	        instructorService.updateInstructorAndDetail(id, instructor, instructorDetail);
	    }
	    
	    @GetMapping("/v1.0/courses/{id}")
	    public List<Course> getAllInstructorCourses(@PathVariable int id){
	    	return instructorService.getAllInstructorCourses(id);
	    }
	    
	    @GetMapping("/v1.0/instructor-details/{id}")
	    public InstructorDetail getInstructorDetail(@PathVariable int id){
	    	return instructorService.getInstructorDetail(id);
	    }
	    
	    @GetMapping("/v1.0/first-or-lastname/{name}")
	    public List<Instructor> getByFirstNameOrLastName(@PathVariable String name){
	    	return instructorService.findByLastnameOrFirstname(name);
	    }
	    
	    @GetMapping("/v1.0/email/{email}")
	    public Instructor getByEmail(@PathVariable String email) {
	    	return instructorService.findByEmail(email);
	    }
	    
	}
