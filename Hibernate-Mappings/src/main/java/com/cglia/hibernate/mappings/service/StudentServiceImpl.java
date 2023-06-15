package com.cglia.hibernate.mappings.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.cglia.hibernate.mappings.entity.Course;
import com.cglia.hibernate.mappings.entity.Student;
import com.cglia.hibernate.mappings.exceptionhandling.GlobalNotFoundException;
import com.cglia.hibernate.mappings.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	    private StudentRepository studentRepository;

	    public StudentServiceImpl(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    @Override
	    public List<Student> findAll() {
	        return studentRepository.findAll();
	    }

	    @Override
	    public Student findById(int id) {
	        Optional<Student> result = studentRepository.findById(id);
	        if (result.isPresent()) {
	            return result.get();
	        } else {
	            throw new GlobalNotFoundException("Student not found with id: " + id);
	        }
	    }

	    @Override
	    public void save(Student student) {
	        studentRepository.save(student);
	    }

	    @Override
	    public void update(Student student, int id) {
	        Optional<Student> result = studentRepository.findById(id);
	        if (result.isPresent()) {
	            Student existingStudent = result.get();
	            existingStudent.setFirstName(student.getFirstName());
	            existingStudent.setLastName(student.getLastName());
	            existingStudent.setEmail(student.getEmail());
	            studentRepository.save(existingStudent);
	        } else {
	            throw new GlobalNotFoundException("Student not found with id: " + id);
	        }
	    }

	    @Override
	    public void deleteById(int id) {
	        studentRepository.deleteById(id);
	    }
	    
	    @Override
	    public List<Course> getStudentCourses(int id){
	    	Optional<Student> result = studentRepository.findById(id);
	    	Student tempStudent = null;
	    	if(result.isPresent()) {
	    		tempStudent = result.get();
	    	}else {
	    		throw new GlobalNotFoundException("student details not found for id :" +id);
	    	}
	    	return tempStudent.getCourses();
	    }

		@Override
		public List<Student> findByLastnameAndFirstname(String lastName, String firstName) {
			List<Student> result = studentRepository.findByLastnameAndFirstname(lastName, firstName);
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("student details not found for given lastName :" +lastName +" and firstName :"+firstName);
			}
			return result;
		}

		@Override
		public List<Student> findByLastnameOrFirstname(String lastName, String firstName) {
			List<Student> result = studentRepository.findByLastnameOrFirstname(lastName, firstName);
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("student details not found for given lastName :" +lastName +" and firstName :"+firstName);
			}
			return result;
		}

		@Override
		public List<Student> findByDobLessThanEqual(String dob) {
			List<Student> result = studentRepository.findByDobLessThanEqual(dob);
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("student details not found with given Date Of Birth : "+dob);
			}
			return result;
		}

		@Override
		public List<Student> findByDobGreaterThanEqual(String dob) {
			List<Student> result = studentRepository.findByDobGreaterThanEqual(dob);
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("student details not found with given Date Of Birth :"+dob);
			}
			return result;
		}

		@Override
		public List<Student> findByDateOfBirthOrderByLastnameDesc(String dob) {
			List<Student> result = studentRepository.findByDateOfBirthOrderByLastnameDesc(dob);
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("student details not found with given Date Of Birth :"+dob);
			}
			return result;
		}
	}
