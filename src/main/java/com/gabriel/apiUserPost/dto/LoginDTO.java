package com.gabriel.apiUserPost.dto;

import com.gabriel.apiUserPost.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
	private String email;
	private String senha;
	
	public LoginDTO(User user) {
		email= user.getEmail();
		senha = user.getSenha();
		
	}
}
