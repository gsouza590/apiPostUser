package com.gabriel.apiUserPost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.apiUserPost.entities.Post;
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	
}
