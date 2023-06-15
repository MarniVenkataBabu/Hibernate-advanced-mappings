package com.cglia.hibernate.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cglia.hibernate.mappings.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
  // that's it no coding required here
	
	//add convenience method to findByLastName or firstName
	
	@Query("select i from Instructor i where i.firstName like %:firstname% or i.lastName like %:lastname%")
	public List<Instructor> findByLastnameOrFirstname(@Param("firstname") String firstname,
			@Param("lastname") String lastname);
	
	@Query("select i from Instructor i where i.email like %:email%")
	public Instructor findByEmail(@Param("email") String email);
}
