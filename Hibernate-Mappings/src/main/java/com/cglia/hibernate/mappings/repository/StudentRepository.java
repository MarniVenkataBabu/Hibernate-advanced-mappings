package com.cglia.hibernate.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cglia.hibernate.mappings.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	  
	 @Query("select u from Student u where u.lastName like %?1% and u.firstName like %?2%")
	public List<Student> findByLastnameAndFirstname(String lastName, String firstName);
	
	 @Query("select u from Student u where u.lastName like %?1% or u.firstName like %?2%")
	public List<Student> findByLastnameOrFirstname(String lastName, String firstName);
	
	 @Query("select u from Student u where u.dateOfBirth <= ?1")
	public List<Student> findByDobLessThanEqual(String dateOfBirth);
	
	 @Query("select u from Student u where u.dateOfBirth >= ?1")
	public List<Student> findByDobGreaterThanEqual(String dateOfBirth);
	
	 @Query("select u from Student u where u.dateOfBirth like ?1 order by u.lastName desc")
	public List<Student> findByDateOfBirthOrderByLastnameDesc(String dateOfBirth);

}
