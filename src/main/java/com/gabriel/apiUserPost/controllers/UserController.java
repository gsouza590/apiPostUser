package com.gabriel.apiUserPost.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.apiUserPost.dto.UserDTO;
import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserServices service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));

	}

	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());

	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO form) {
		User user = service.fromDTO(form);
		user = service.insert(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping (value="/{id}")
	public ResponseEntity<Void> update (@RequestBody UserDTO form, @PathVariable String id) {
		User user = service.fromDTO(form);
		user.setId(id);
		user = service.update(user);
		
		return ResponseEntity.noContent().build();

	}
	
	
}
