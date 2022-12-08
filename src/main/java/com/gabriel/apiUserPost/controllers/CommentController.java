package com.gabriel.apiUserPost.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.apiUserPost.dto.CommentDTO;
import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentService service;
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll() {

		List<Comment> list = service.findAll();
		List<CommentDTO> listDto = list.stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
}
