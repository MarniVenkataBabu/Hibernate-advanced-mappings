package com.cglia.hibernate.mappings.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cglia.hibernate.mappings.entity.Course;
import com.cglia.hibernate.mappings.entity.Instructor;
import com.cglia.hibernate.mappings.entity.InstructorDetail;
import com.cglia.hibernate.mappings.exceptionhandling.GlobalNotFoundException;
import com.cglia.hibernate.mappings.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	    private InstructorRepository instructorRepository;

	    @Autowired
	    public InstructorServiceImpl(InstructorRepository instructorRepository) {
	        this.instructorRepository = instructorRepository;
	    }

	    @Override
	    @Transactional
	    public List<Instructor> findAll() {
	        return instructorRepository.findAll();
	    }

	    @Override
	    @Transactional
	    public Instructor findById(int id) {
	        return instructorRepository.findById(id)
	                .orElseThrow(() -> new GlobalNotFoundException("Instructor not found with given id : " + id));
	    }

	    @Override
	    @Transactional
	    public void save(Instructor instructor, InstructorDetail instructorDetail) {
	        try {
	            Instructor tempInstructor = new Instructor();
	            
	            tempInstructor.setFirstName(instructor.getFirstName());
	            tempInstructor.setLastName(instructor.getLastName());
	            tempInstructor.setEmail(instructor.getEmail());
	            
	            InstructorDetail tempInstructorDetail = new InstructorDetail();
	            
	            tempInstructorDetail.setYoutubeChannel(instructorDetail.getYoutubeChannel());
	            tempInstructorDetail.setHobby(instructorDetail.getHobby());
	            tempInstructorDetail.setExperienceInYears(instructorDetail.getExperienceInYears());
	            
	            tempInstructor.setInstructorDetail(tempInstructorDetail);
	            instructorRepository.save(tempInstructor);
	        } catch (Exception e) {
	            throw new GlobalNotFoundException("Failed to save instructor and instructor detail.", e);
	        }
	    }



	    @Override
	    @Transactional
	    public void update(Instructor instructor, int id) {
	        Instructor existingInstructor = findById(id);
	        existingInstructor.setFirstName(instructor.getFirstName());
	        existingInstructor.setLastName(instructor.getLastName());
	        existingInstructor.setEmail(instructor.getEmail());
	        instructorRepository.save(existingInstructor);
	    }

	    @Override
	    @Transactional
	    public void deleteById(int id) {
	        instructorRepository.deleteById(id);
	    }

	    @Override
	    @Transactional
	    public void updateInstructorAndDetail(int instructorId, Instructor updatedInstructor,
	            InstructorDetail updatedInstructorDetail) {
	        Instructor instructor = findById(instructorId);
	        instructor.setFirstName(updatedInstructor.getFirstName());
	        instructor.setLastName(updatedInstructor.getLastName());
	        instructor.setEmail(updatedInstructor.getEmail());
	        instructor.setInstructorDetail(updatedInstructorDetail);
	    }

		@Override
		public List<Course> getAllInstructorCourses(int id) {
			Optional<Instructor> result = instructorRepository.findById(id);
			Instructor tempInstructor = null;
			if(result.isPresent()) {
				tempInstructor = result.get();
			}else {
				throw new GlobalNotFoundException("Instructor details not found with given Id :"+id);
			}
			return tempInstructor.getCourses();
		}

		@Override
		public InstructorDetail getInstructorDetail(int id) {
			Optional<Instructor> result = instructorRepository.findById(id);
			Instructor tempInstructor = null;
			if(result.isPresent()) {
				tempInstructor = result.get();
			}else {
				throw new GlobalNotFoundException("Instructor details not found with given Id :"+id);
			}
			return tempInstructor.getInstructorDetail();
		}

		@Override
		public List<Instructor> findByLastnameOrFirstname(String name) {
			List<Instructor> result = instructorRepository.findByLastnameOrFirstname(name,name); 
			if(result.isEmpty()) {
				throw new GlobalNotFoundException("Instructor details not found with given name :"+name);
			}
			return result;
		}

		@Override
		public Instructor findByEmail(String email) {
		Instructor result = instructorRepository.findByEmail(email);
			if(result == null) {
				throw new GlobalNotFoundException("Instructor details not found with given email :"+email);
			}
			return result;
		}
	}
