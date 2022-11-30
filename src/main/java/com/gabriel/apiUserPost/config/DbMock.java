package com.gabriel.apiUserPost.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.UserRepository;

@Configuration
public class DbMock implements CommandLineRunner {

	@Autowired
	private UserRepository userRep;
	@Override
	public void run(String... args) throws Exception {
		userRep.deleteAll();
		
		User maria = new User(null,"Maria","maria@gmail.com");
		User joao = new User(null,"Joao","joao@gmail.com");
		User carlos = new User(null,"Carlos","carlos@gmail.com");
	
		userRep.saveAll(Arrays.asList(maria, joao, carlos));
	}

}
