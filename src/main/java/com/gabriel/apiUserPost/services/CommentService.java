package com.gabriel.apiUserPost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.dto.CommentDTO;
import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.repositories.CommentRepository;
import com.gabriel.apiUserPost.services.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	CommentRepository rep;
	
	
	public Comment insert (Comment comment) {
		return rep.insert(comment);
	}
	
	public List<Comment> findAll() {
		return rep.findAll();
	}


	public Comment findById(String id) {
		Optional<Comment> comment = rep.findById(id);
		return comment.orElseThrow(() -> new ObjectNotFoundException("Comentário não encontrado"));
		}
	

	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}

	public Comment update(Comment comment) {
		Optional<Comment> comment1 = rep.findById(comment.getId());
		Comment newcomment = comment1.orElseThrow(() -> new ObjectNotFoundException("Comentário não encontrado"));

		updateData(newcomment, comment);

		return rep.save(newcomment);
	}

	private void updateData(Comment newcomment, Comment comment) {

		newcomment.setText(comment.getText());

	}
	
	
	
	
	
	public Comment fromDTO(CommentDTO dto) {
		
		return new Comment(dto.getId(), dto.getText(),dto.getDate(),dto.getAuthor());
	}
}
