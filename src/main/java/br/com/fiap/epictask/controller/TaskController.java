package br.com.fiap.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	@GetMapping("/task")
	public String index() {
		return "tasks";
	}
	
	@PostMapping("/task")
	public String save(Task task) {
		repository.save(task);
		return "tasks";
	}
	
	@RequestMapping("/task/new")
	public String create() {
		return "task-form";
	}

}
