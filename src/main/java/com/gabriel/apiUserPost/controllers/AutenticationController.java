package com.gabriel.apiUserPost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.apiUserPost.dto.LoginDTO;
import com.gabriel.apiUserPost.dto.LoginTokenDTO;
import com.gabriel.apiUserPost.entities.User;
import com.gabriel.apiUserPost.services.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticationController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> doLogin(@RequestBody LoginDTO dto) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
		var authentication = manager.authenticate(authenticationToken);
		var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new LoginTokenDTO(tokenJWT));
	}
}
