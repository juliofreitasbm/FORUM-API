package com.mateus.forum.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mateus.forum.controller.dto.TopicDTO;
import com.mateus.forum.model.Topic;
import com.mateus.forum.repository.CourseRepository;
import com.mateus.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<TopicDTO> lista(){
		List<Topic> topics = topicRepository.findAll();
		return TopicDTO.converter(topics);
	}
	
	@PostMapping
	public ResponseEntity<TopicDTO> cadastrar(@RequestBody @Validated TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body( new TopicDTO(topic));
	}
	
	@GetMapping("/{id}")
	public TopicDTO detalhar(@PathVariable Long id) {
		Topic topic = topicRepository.getOne(id);
		return new TopicDTO(topic);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDTO> atualizar(@PathVariable Long id, @RequestBody @Validated TopicForm form) {
		Topic topic = form.atualizar(id, topicRepository);
		return ResponseEntity.ok(new TopicDTO(topic) );
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		topicRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
