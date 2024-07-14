package com.mateus.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.forum.model.Course;

public interface CourseRepository  extends JpaRepository<Course, Long>{

	Course findByName(String name);

}
