package br.com.fiap.epictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	

}
