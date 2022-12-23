package com.gabriel.apiUserPost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.gabriel.apiUserPost.entities.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	UserDetails findByEmail(String email);

	
}
