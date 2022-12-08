package com.gabriel.apiUserPost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.repositories.CommentRepository;
import com.gabriel.apiUserPost.services.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	CommentRepository rep;
	
	
	public List<Comment> findAll() {
		return rep.findAll();
	}


	public Comment findById(String id) {
		Optional<Comment> comment = rep.findById(id);
		return comment.orElseThrow(() -> new ObjectNotFoundException("Comentário não encontrado"));
		}
}
