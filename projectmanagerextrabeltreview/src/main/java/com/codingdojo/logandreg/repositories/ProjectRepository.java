package com.codingdojo.logandreg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.logandreg.models.Project;
import com.codingdojo.logandreg.models.User;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	List<Project> findAll();
	Project findByIdIs(Long id);
	List<Project> findAllByUsers(User user);
	List<Project> findAllByLead(User user);
	List<Project> findByUsersNotContains(User user);
}
