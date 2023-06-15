package com.cglia.hibernate.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cglia.hibernate.mappings.entity.InstructorDetail;

@Repository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer>{

	@Query("select i from InstructorDetail i where i.experienceInYears >= ?1")
	public List<InstructorDetail> findByExperienceGreaterThanEqual(int experienceInYears);
}
