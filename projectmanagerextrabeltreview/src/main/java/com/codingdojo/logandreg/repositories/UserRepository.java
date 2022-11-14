package com.codingdojo.logandreg.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.logandreg.models.Project;
import com.codingdojo.logandreg.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();
	Optional<User>findByEmail(String email);
	Optional<User>findById(Long id);
	List<User> findAllByProjects(Project project);
	List<User> findByProjectsNotContains(Project project);
	
}
