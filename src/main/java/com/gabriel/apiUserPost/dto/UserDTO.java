package com.gabriel.apiUserPost.dto;

import java.io.Serializable;

import com.gabriel.apiUserPost.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String email;
	
	public UserDTO(User user) {
		id = user.getId();
		name=user.getName();
		email= user.getEmail();
	}
}
