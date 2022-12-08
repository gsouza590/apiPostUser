package com.gabriel.apiUserPost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository rep;
	
	
	public List<Comment> findAll() {
		return rep.findAll();
	}
}
