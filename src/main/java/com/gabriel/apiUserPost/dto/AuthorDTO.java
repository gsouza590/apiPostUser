package com.gabriel.apiUserPost.dto;

import java.io.Serializable;

import com.gabriel.apiUserPost.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	public AuthorDTO(User user) {
		id=user.getId();
		name= user.getName();
	}
}
