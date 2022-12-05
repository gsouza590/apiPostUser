package com.gabriel.apiUserPost.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabriel.apiUserPost.dto.AuthorDTO;
import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.PostRepository;
import com.gabriel.apiUserPost.repositories.UserRepository;

@Configuration
public class DbMock implements CommandLineRunner {

	@Autowired
	private UserRepository userRep;
	@Autowired 
	private PostRepository postRep;
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRep.deleteAll();
		postRep.deleteAll();
		
		User maria = new User(null,"Maria","maria@gmail.com");
		User joao = new User(null,"Joao","joao@gmail.com");
		User carlos = new User(null,"Carlos","carlos@gmail.com");
		userRep.saveAll(Arrays.asList(maria, joao, carlos));
	
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Viagem Sao Paulo",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/05/2018"), "Bom dia ", "Melhor Dia", new AuthorDTO(maria));
		
		postRep.saveAll(Arrays.asList(post1,post2));
	}

}
