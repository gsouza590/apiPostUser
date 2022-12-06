package com.gabriel.apiUserPost.services;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> findByTitle(String text){
		return rep.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate ){
		maxDate = new Date (maxDate.getTime()+ 24*60*60*1000);
		return rep.fullSearch(text, minDate, maxDate);
		
	}
	
	
}