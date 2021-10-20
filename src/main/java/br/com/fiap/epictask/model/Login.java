package br.com.fiap.epictask.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class Login {
	
	@NotBlank @Email
	private String username;
	
	@NotBlank
	private String password;

	public UsernamePasswordAuthenticationToken getAuthentication() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
}
