package com.gabriel.apiUserPost.dto;

import java.io.Serializable;
import java.util.Date;

import com.gabriel.apiUserPost.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private Date date;
	private AuthorDTO author;
	
	public CommentDTO(Comment comment) {
		
		id = comment.getId();
		text= comment.getText();
		date= comment.getDate();
		author= comment.getAuthor();
	}
}
