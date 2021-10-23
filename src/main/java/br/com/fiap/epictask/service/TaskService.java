package br.com.fiap.epictask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.controller.NotAllowedException;
import br.com.fiap.epictask.exception.TaskNotFoundException;
import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private MessageSource message;
	
	public Page<Task> listTasks(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public void save(Task task, RedirectAttributes redirect) {
		repository.save(task);
		redirect.addFlashAttribute("message",
				message.getMessage("task.new.success", null, LocaleContextHolder.getLocale()));
	}

	public void hold(Long id, Authentication auth) {
		Optional<Task> optional = repository.findById(id);
		if (optional.isEmpty())
			throw new TaskNotFoundException("Tarefas não encontrada");

		Task task = optional.get();

		if (task.getUser() != null)
			throw new NotAllowedException("A tarefa já está atribuída");

		User user = (User) auth.getPrincipal();
		task.setUser(user);

		repository.save(task);

	}

	public void release(Long id, Authentication auth) {
		Optional<Task> optional = repository.findById(id);
		if (optional.isEmpty())
			throw new TaskNotFoundException("Tarefas não encontrada");

		Task task = optional.get();
		User user = (User) auth.getPrincipal();

		if (!task.getUser().equals(user))
			throw new NotAllowedException("A tarefa está tribuída para outra pessoa");

		task.setUser(null);

		repository.save(task);

	}

}
