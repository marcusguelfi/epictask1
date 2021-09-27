package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{user.name.blank}")
	private String name;
	
	@NotBlank(message = "{user.email.blank}")
	@Email(message = "{user.email.invalid}")
	private String email;
	
	@Size(min = 8, message = "{user.password.size}")
	private String password;
	
	

}
