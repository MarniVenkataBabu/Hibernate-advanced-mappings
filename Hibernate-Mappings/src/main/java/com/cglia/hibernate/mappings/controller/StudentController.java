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
import com.cglia.hibernate.mappings.entity.Student;
import com.cglia.hibernate.mappings.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/v1.0")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/v1.0/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping("/v1.0")
    public void addStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("/v1.0/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.update(student, id);
    }

    @DeleteMapping("/v1.0/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
    }
    
    @GetMapping("/v1.0/courses/{id}")
    public List<Course> getAllCoursesForStudent(@PathVariable int id){
    	return studentService.getStudentCourses(id);
    }
    
    @GetMapping("/v1.0/{lastName}/and/{firstName}")
    public List<Student> findByLastnameAndFirstname(@PathVariable String lastName,
    		                                        @PathVariable String firstName) {
    	return studentService.findByLastnameAndFirstname(lastName,firstName);
    }
    
    @GetMapping("/v1.0/{lastName}/or/{firstName}")
    public List<Student> findByLastnameORFirstname(@PathVariable String lastName,
    		                                        @PathVariable String firstName) {
    	return studentService.findByLastnameOrFirstname(lastName,firstName);
    }
    @GetMapping("/v1.0/dob-less-than/{dob}")
    public List<Student> findByDobLessThan(@PathVariable String dob){
    	return studentService.findByDobLessThanEqual(dob);
    }
    
    @GetMapping("/v1.0/dob-greater-than/{dob}")
    public List<Student> findByDobGreatherThan(@PathVariable String dob){
    	return studentService.findByDobGreaterThanEqual(dob);
    }
    
    @GetMapping("/v1.0/dob-orderby-lastname/{dob}")
    public List<Student> findByDateOfBirthOrderByLastnameDesc(@PathVariable String dob){
    	return studentService.findByDateOfBirthOrderByLastnameDesc(dob);
    }
}

