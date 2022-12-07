package com.gabriel.apiUserPost.controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.apiUserPost.controllers.utils.URL;
import com.gabriel.apiUserPost.dto.AuthorDTO;
import com.gabriel.apiUserPost.dto.PostDTO;
import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.services.PostServices;
import com.gabriel.apiUserPost.services.UserServices;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostServices service;
	@Autowired
	private UserServices userService;
	
	
	@PostMapping(value = "/user/{id}")
	public ResponseEntity<PostDTO> insert(@RequestBody PostDTO form, @PathVariable String id) {
		
		User user = userService.findById(id);
		AuthorDTO dto = new AuthorDTO(user);
		
		Post post = service.fromDTO(form);
		post.setAuthor(dto);
		post = service.insert(post);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);

	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value= "text", defaultValue = "") String text) {

		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping(value="/fullsearch")
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping (value="/{id}")
	public ResponseEntity<Void> update (@RequestBody PostDTO form, @PathVariable String id) {
		Post post = service.fromDTO(form);
		post.setId(id);
		post = service.update(post);
		
		return ResponseEntity.noContent().build();

	}
}
