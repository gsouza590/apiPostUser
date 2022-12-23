package com.gabriel.apiUserPost.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabriel.apiUserPost.dto.AuthorDTO;
import com.gabriel.apiUserPost.dto.CommentDTO;
import com.gabriel.apiUserPost.entities.Comment;
import com.gabriel.apiUserPost.entities.Post;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.repositories.CommentRepository;
import com.gabriel.apiUserPost.repositories.PostRepository;
import com.gabriel.apiUserPost.repositories.UserRepository;

@Configuration
public class DbMock implements CommandLineRunner {

	@Autowired
	private UserRepository userRep;
	@Autowired 
	private PostRepository postRep;
	@Autowired
	private CommentRepository comRep;
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRep.deleteAll();
		postRep.deleteAll();
		comRep.deleteAll();
		
		User maria = new User(null,"Maria","maria@gmail.com","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
		User joao = new User(null,"Joao","joao@gmail.com","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
		User carlos = new User(null,"Carlos","carlos@gmail.com","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
		userRep.saveAll(Arrays.asList(maria, joao, carlos));
	
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Viagem Sao Paulo",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/05/2018"), "Bom dia ", "Melhor Dia", new AuthorDTO(maria));
		
		postRep.saveAll(Arrays.asList(post1,post2));
		
		Comment c1 = new Comment(null,"Boa viagem Mano!", sdf.parse("21/03/2018"), new AuthorDTO(joao));
		Comment c2 = new Comment(null,"Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(carlos));
		Comment c3 = new Comment(null,"Ótimo Dia!", sdf.parse("21/07/2018"), new AuthorDTO(joao));
		
		comRep.saveAll(Arrays.asList(c1,c2,c3));
		
		post1.getComments().addAll(Arrays.asList(new CommentDTO(c1),new CommentDTO(c2)));
		post2.getComments().add(new CommentDTO(c3));
		postRep.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRep.save(maria);
	};

}
