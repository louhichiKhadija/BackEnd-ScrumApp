package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.entities.Project;
import com.example.entities.User;
import com.example.repositories.ProjectReprository;
import com.example.repositories.UserRepository;

@Service ("projectService")
public class ServiceProjectImpl implements ServiceProject {

	@Autowired 
	private ProjectReprository projectReprository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<Project> findProject(long id) {
	
		return projectReprository.findById( id);
	}
	
	@Override
	public void addProject(Project project) {
		String email= SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		Set<User> users=project.getUser();
		users.add(user);
		project.setUser(users);
		projectReprository.save(project);
	}
	
	@Override
	public List<Project> getProjectsByUser(){
		String email= SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		if(user !=null)
		return projectReprository.findByUserId(user.getId());
		else return null;
	}


}
