package com.gabriel.apiUserPost.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.gabriel.apiUserPost.dto.AuthorDTO;
import com.gabriel.apiUserPost.dto.CommentDTO;
import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.services.CommentService;
import com.gabriel.apiUserPost.services.PostServices;
import com.gabriel.apiUserPost.services.UserServices;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentService service;
	@Autowired
	private UserServices userService;
	@Autowired
	private PostServices postService;
	
	
	@PostMapping(value = "/user/{id}/post/{id2}")
	
	public ResponseEntity<CommentDTO> insert(@RequestBody CommentDTO form, @PathVariable String id,  @PathVariable String id2) {
		
		// Carrego o usuario do Id
		User user = userService.findById(id);
		AuthorDTO authorDto = new AuthorDTO(user);
		
		//Carrego o Post pelo Id
		Post post = postService.findById(id2);
		
		// Criacao do comentario
		Comment comment = service.fromDTO(form);
		comment.setAuthor(authorDto);
		comment= service.insert(comment);
		
		// Relacionar o Comentário no Post
		
		form = new CommentDTO(comment);
		post = postService.addComment(post, form);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll() {

		List<Comment> list = service.findAll();
		List<CommentDTO> listDto = list.stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> findById(@PathVariable String id) {

		Comment comment = service.findById(id);
		CommentDTO dto = new CommentDTO(comment);
		return ResponseEntity.ok().body(dto);

	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping (value="/{id}")
	public ResponseEntity<Void> update (@RequestBody CommentDTO form, @PathVariable String id) {
		Comment comment = service.fromDTO(form);
		comment.setId(id);
		comment = service.update(comment);
		
		return ResponseEntity.noContent().build();

	}
}
