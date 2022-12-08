package com.gabriel.apiUserPost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.apiUserPost.entities.Comment;
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

	
}
