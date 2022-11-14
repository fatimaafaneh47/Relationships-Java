package com.codingdojo.logandreg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.logandreg.models.Project;
import com.codingdojo.logandreg.models.User;
import com.codingdojo.logandreg.repositories.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
    
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public List<Project> allProjects() {
        return projectRepository.findAll();
    }
 
    public Project createProject(Project project) {
    	
        return projectRepository.save(project);
    }

    public Project findProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            return null;
        }
    }
    public Project updateProject(Project project) {
    	return projectRepository.save(project);
    }
    public void deleteProject(Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if(optionalProject.isPresent()) {
			projectRepository.deleteById(id);
		}
    }
    public List<Project> getAssignedUsers(User user){
		return projectRepository.findAllByUsers(user);
	}
	
	public List<Project> getUnassignedUsers(User user){
		return projectRepository.findByUsersNotContains(user);
	}
	
	public List<Project> getLeadProjects(User user){
		return projectRepository.findAllByLead(user);
	}
	

}
