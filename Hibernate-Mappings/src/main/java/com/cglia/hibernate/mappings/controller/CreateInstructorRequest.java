package com.cglia.hibernate.mappings.controller;

import com.cglia.hibernate.mappings.entity.Instructor;
import com.cglia.hibernate.mappings.entity.InstructorDetail;

public class CreateInstructorRequest {
	
    private Instructor instructor;
    private InstructorDetail instructorDetail;

    // Default constructor
    public CreateInstructorRequest() {
    }

    // Getters and setters
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }
}

