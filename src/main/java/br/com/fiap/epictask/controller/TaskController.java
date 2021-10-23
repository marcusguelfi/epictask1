package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Value("${default.page.size}")
	private static int DefaultPageSize;
	
	@Autowired
	public TaskService service;

	@GetMapping
	public ModelAndView index(@RequestParam int p, @RequestParam int r, @RequestParam List<Sort.Order> o) {
		if(r == 0) r = DefaultPageSize;
		Pageable pageable = PageRequest.of(p, r, Sort.by(o));
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("tasks", service.listTasks(pageable));
		return modelAndView;
	}

	@PostMapping
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			return "task-form";
		service.save(task, redirect);
		return "redirect:/task";
	}

	@RequestMapping("new")
	public String create(Task task) {
		return "task-form";
	}

	@GetMapping("/hold/{id}")
	public String hold(@PathVariable Long id, Authentication auth) {
		service.hold(id, auth);
		return "redirect:/task";
	}

	@GetMapping("/release/{id}")
	public String release(@PathVariable Long id, Authentication auth) {
		service.release(id, auth);
		return "redirect:/task";
	}

}
