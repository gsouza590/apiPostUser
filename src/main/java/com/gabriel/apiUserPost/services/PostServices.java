package com.gabriel.apiUserPost.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.repositories.PostRepository;
import com.gabriel.apiUserPost.services.exception.ObjectNotFoundException;

@Service
public class PostServices {

	@Autowired
	private PostRepository rep;

	
	public Post findById(String id) {
		Optional<Post> post = rep.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}