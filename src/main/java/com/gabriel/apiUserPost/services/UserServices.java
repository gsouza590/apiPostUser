package com.gabriel.apiUserPost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository rep;
	
	public List<User> findAll(){
		return rep.findAll();
	}
}
