package com.cglia.hibernate.mappings.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cglia.hibernate.mappings.entity.InstructorDetail;
import com.cglia.hibernate.mappings.service.InstructorDetailService;

@RestController
@RequestMapping("/api/instructordetails")
public class InstructorDetailController {
       
	private InstructorDetailService instructorDetailService;
	
	public InstructorDetailController(InstructorDetailService instructorDetailService) {
		this.instructorDetailService = instructorDetailService;
	}
	
	@GetMapping("/v1.0")
	public List<InstructorDetail> getAllinstructorDetails(){
		return instructorDetailService.findAll();
	}
	
	@GetMapping("/v1.0/{id}")
	public InstructorDetail getSingleInstructorDetail(@PathVariable int id) {
		return instructorDetailService.findById(id);
	}
	
	@GetMapping("/v1.0/experience-greater-or-equal/{experience}")
	public List<InstructorDetail> getAllInstructorDetail(@PathVariable int experience){
		return instructorDetailService.findByExperienceGreaterThanEqual(experience);
	}
	
	@DeleteMapping("/v1.0/{id}")
	 public void deleteInstrDetail(@PathVariable int id) {
		instructorDetailService.deleteById(id);
	}
	
	
}
