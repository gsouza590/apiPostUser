package com.gabriel.apiUserPost.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gabriel.apiUserPost.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDTO {

	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;
	private List<CommentDTO> comments = new ArrayList<CommentDTO>();
	
	public PostDTO(String id, Date date, String title, String body, AuthorDTO author) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public PostDTO(Post post) {
		
		id= post.getId();
		date= post.getDate();
		title=post.getTitle();
		body=post.getBody();
		author=post.getAuthor();
		comments=post.getComments();
	} 
	
	
}
