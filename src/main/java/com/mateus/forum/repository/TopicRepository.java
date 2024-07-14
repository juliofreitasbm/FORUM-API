package com.mateus.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
