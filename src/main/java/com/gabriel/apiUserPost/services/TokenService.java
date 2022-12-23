package com.gabriel.apiUserPost.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabriel.apiUserPost.entities.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String createToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API UserPost").withSubject(user.getEmail()).withExpiresAt(dataExpiracao())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("erro ao gerrar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var alg = Algorithm.HMAC256(secret);
			
			return JWT.require(alg).withIssuer("API UserPost").build().verify(tokenJWT).getSubject();
			
			
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token inválido ou expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
