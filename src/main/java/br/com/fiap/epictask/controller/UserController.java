package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.service.UserSevice;

@Controller
@RequestMapping("/user")
public class UserController {

	@Value("${default.page.size}")
	private static int DefaultPageSize;

	@Autowired
	private UserSevice service;

	@GetMapping
	public ModelAndView index(@RequestParam int p, @RequestParam int r, @RequestParam List<Sort.Order> o) {
		if (r == 0)
			r = DefaultPageSize;
		Pageable pageable = PageRequest.of(p, r, Sort.by(o));
		ModelAndView modelAndView = new ModelAndView("users");
		Page<User> users = service.listUsers(pageable);
		modelAndView.addObject("users", users);
		System.out.println(users);
		return modelAndView;
	}

	@RequestMapping("new")
	public String create(User user) {
		return "user-form";
	}

	@PostMapping
	public String save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			return "user-form";
		service.save(user, redirect);
		return "redirect:user";
	}
	
	@RequestMapping("edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("user-edit-form");
		modelAndView.addObject("user", service.get(id));
		return modelAndView;
	}
	
	@PutMapping
	public String edit(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			return "user-edit-form";
		service.edit(user, redirect);
		return "redirect:user";
	}
	
	@DeleteMapping("remove/{id}")
	public String remove(@PathVariable Long id, RedirectAttributes redirect) {
		service.remove(id, redirect);
		return "redirect:user";
	}

}
