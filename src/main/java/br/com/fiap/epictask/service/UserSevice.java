package br.com.fiap.epictask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.exception.UserNotFoundException;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

@Service
public class UserSevice {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private MessageSource messages;

	public Page<User> listUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public User get(Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty())
			throw new UserNotFoundException("Usuário não encontrada");

		return optional.get();
	}
	
	
	public void save(User user, RedirectAttributes redirect) {
		user.setPassword(AuthenticationService.getPasswordEncoder().encode(user.getPassword()));
		repository.save(user);
		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.newuser", null, LocaleContextHolder.getLocale()));
	}
	
	public void edit (User user, RedirectAttributes redirect) {
		if(user.getPassword() != null) {
			user.setPassword(AuthenticationService.getPasswordEncoder().encode(user.getPassword()));
		}
		repository.save(user);
		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.edituser", null, LocaleContextHolder.getLocale()));
	}

	public void remove(Long id, RedirectAttributes redirect) {
		if(taskService.hasTasksToUser(id)) {
			redirect.addFlashAttribute("message",
					messages.getMessage("message.fail.removeuser.constraint.task", null, LocaleContextHolder.getLocale()));
		}else {
		repository.deleteById(id);
		redirect.addFlashAttribute("message",
				messages.getMessage("message.success.removeuser", null, LocaleContextHolder.getLocale()));
		}
	}

}
