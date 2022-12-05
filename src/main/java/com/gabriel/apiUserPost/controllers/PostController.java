package com.gabriel.apiUserPost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.services.PostServices;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostServices service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);

	}

}
