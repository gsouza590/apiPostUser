package com.gabriel.apiUserPost.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gabriel.apiUserPost.dto.AuthorDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Document
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String text;
	private Date date;
	private AuthorDTO author;
	
}
