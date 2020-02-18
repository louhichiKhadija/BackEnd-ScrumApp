package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.entities.Project;
import com.example.entities.User;
import com.example.repositories.ProjectReprository;

@Service ("projectService")
public class ServiceProjectImpl implements ServiceProject {

	@Autowired 
	private ProjectReprository projectReprository;
	
	@Autowired
	private IServiceUser userService;
	
	@Override
	public Optional<Project> findProject(long id) {
	
		return projectReprository.findById( id);
	}
	
	@Override
	public void addProject(Project project, User user) {
		User u = userService.findById(user.getId());
		projectReprository.save(project);
	}
	

}
