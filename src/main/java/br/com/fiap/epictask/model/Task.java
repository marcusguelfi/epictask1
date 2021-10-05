package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Task {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{task.title.blank}")
	private String title;
	
	@Size(min = 15, message = "A descrição deve ter pelo menos 15 caracteres")
	private String description;
	
	@Min(value = 10, message = "A pontuação mínima é 10")
	@Max(value = 500, message = "A pontuação máxima é 500")
	private int points;
	
	@Min(value = 0, message = "{task.status.min}")
	@Max(value = 100, message = "{task.status.max}")
	private int status;
	
	@ManyToOne
	private User user;

}
