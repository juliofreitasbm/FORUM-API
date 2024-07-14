package com.mateus.forum.controller;
import com.mateus.forum.model.Course;
import com.mateus.forum.model.Topic;
import com.mateus.forum.repository.CourseRepository;
import com.mateus.forum.repository.TopicRepository;
import com.sun.istack.NotNull;

public class TopicForm {
	@NotNull 
	private String  title;
	@NotNull
	private String message;
	@NotNull
	private String courseName;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Topic converter(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(courseName);
		return new Topic(title, message, course);
	}
	public Topic atualizar(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getOne(id);
		topic.setTitle(this.title);
		topic.setMessage(this.message);
		
		return topic;
	}
	
	

}
