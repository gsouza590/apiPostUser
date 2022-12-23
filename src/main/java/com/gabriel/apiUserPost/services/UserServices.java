package com.gabriel.apiUserPost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.dto.UserDTO;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.UserRepository;
import com.gabriel.apiUserPost.services.exception.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository rep;

	public List<User> findAll() {
		return rep.findAll();
	}

	public User findById(String id) {
		Optional<User> user = rep.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return rep.insert(user);
	}
	
	public User update(User user) {
		Optional<User> user1 = rep.findById(user.getId());
	    User newUser = user1.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

		updateData(newUser, user);
		
		return rep.save(newUser);
	}

	private void updateData(User newUser, User user) {

		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());

	}

	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getSenha());
	}
}