package com.gabriel.apiUserPost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.UserRepository;
import com.gabriel.apiUserPost.services.exception.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository rep;
	
	public List<User> findAll(){
		return rep.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = rep.findById(id);
	
		return user.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
