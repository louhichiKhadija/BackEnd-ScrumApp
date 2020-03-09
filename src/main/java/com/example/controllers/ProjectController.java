package com.example.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.entities.*;
import com.example.services.ServiceProject;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired 
	private ServiceProject projectService;
	
	
	
	@GetMapping(value="/getone/{id}")
	public Optional<Project>  getProject(@PathVariable(value = "id") long id)
	{
		return projectService.findProject(id);
		
	}
	
	@PostMapping(value="/add")
	public void addProject(@RequestBody Project project){
		projectService.addproject(project);
	}
	
	@PostMapping(value="/add-userstory/{idProject}")
	public void addUserStory(@RequestBody Userstory u, @PathVariable long  idProject) {
		projectService.addUserstory(u, idProject);
	}
	
	@PostMapping(value="/add-tache/{idUserStory}")
	public void addTache(@RequestBody Taches t, @PathVariable int  idUserStory) {
		projectService.addTache(t, idUserStory);
	}

		
	


}
