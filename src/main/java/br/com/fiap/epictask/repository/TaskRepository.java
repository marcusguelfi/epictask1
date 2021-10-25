package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.epictask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	Page<Task> findByTitleLike(String title, Pageable pageable);
	
	long countByUserId(long id);
	
	@Query(value="select SUM(points) FROM Task where status=100 AND user_id = :id", nativeQuery = true)
	Integer findPointsOfConludedTasksByUserId(@Param("id") Long id);
	
	@Query(value="select * from Task where status=100", nativeQuery = true)
	Page<Task> findAllTasksConclued(Pageable pageable);
	
	@Query(value="select * from Task where status < 100", nativeQuery = true)
	Page<Task> findAllTasksNotConclued(Pageable pageable);
	
}
