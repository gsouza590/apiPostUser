package com.gabriel.apiUserPost.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty @Length(min = 6)
	private String senha;
	
	public UserDTO(User user) {
		id = user.getId();
		name=user.getName();
		email= user.getEmail();
		senha= user.getSenha();
	}
}
