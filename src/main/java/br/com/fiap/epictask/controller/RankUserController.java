package br.com.fiap.epictask.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.RankUser;
import br.com.fiap.epictask.service.TaskService;
import br.com.fiap.epictask.service.UserSevice;

@Controller
@RequestMapping("/ranking")
public class RankUserController {
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private UserSevice userService;

	@GetMapping
	public ModelAndView index(
			@RequestParam(defaultValue = "0") int p, 
			@RequestParam(defaultValue = "${default.page.size}") int r, 
			@RequestParam(defaultValue = "${default.sort.order}", required = false) Sort.Direction o,
			@RequestParam(defaultValue = "${default.sort.collum}", required = false ) String s ){
		
		Pageable pageable = PageRequest.of(p, r, Sort.by(o,s));
		ModelAndView modelAndView = new ModelAndView("rank-users");
		List<RankUser> users = userService.listUsers(Pageable.unpaged()).stream().map(u->{
			return new RankUser(u,taskService.getPointsByUser(u));
		}).sorted().collect(Collectors.toList());
		
		Page<RankUser> rankusers = new PageImpl<RankUser>(users , pageable, users.size());
		modelAndView.addObject("rankusers", rankusers);
		System.out.println(users);
		return modelAndView;
	}
}
