package com.gabriel.apiUserPost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.apiUserPost.entities.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
}
