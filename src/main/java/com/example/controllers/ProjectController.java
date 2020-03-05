package com.example.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Project;

import com.example.services.ServiceProject;

@RestController
public class ProjectController {
@Autowired 
private ServiceProject projectService;

@GetMapping(value="/project/{id}")
public Optional<Project>  getProject(@PathVariable(value = "id") long id)
{
	return projectService.findProject(id);
	
}
@PostMapping(value="/add-project")
public void addProject(@RequestBody Project project) {
	projectService.addProject(project);
}



}
