package com.example.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ConfirmationToken;
import com.example.entities.Project;
import com.example.entities.User;
import com.example.repositories.ConfirmationTokenRepository;
import com.example.services.ServiceProject;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
@Autowired 
private ServiceProject projectService;

@Autowired
private ConfirmationTokenRepository confirmationTokenRepository;

@GetMapping(value="/project/{id}")
public Optional<Project>  getProject(@PathVariable(value = "id") long id)
{
	return projectService.findProject(id);
	
}
@PostMapping(value="/add-project")
public void addProject(@RequestBody Project project) {
	projectService.addProject(project);
}

@GetMapping(value="/getAllProjects")
public List<Project>  getProjects()
{
	return projectService.getProjectsByUser();
}

@PostMapping(value="/invit-member/{id}")
public ResponseEntity<?> invitMember(@RequestBody String email, @PathVariable(value = "id") long id ){
	Project project=projectService.findProject(id).get();
	if(project==null) return new ResponseEntity<>("Please check the link !!",HttpStatus.BAD_REQUEST);
	if(projectService.sendInvitation(project, email))
	return new ResponseEntity<>(true,HttpStatus.OK);
	return new ResponseEntity<>("This email is incorrect !!",HttpStatus.BAD_REQUEST);
}

@GetMapping(value="/acceptInvitation/{id}")
public ResponseEntity<?> acceptInvitation(@PathVariable(value = "id") long id, @RequestParam("token")String confirmationToken){
	Project project=projectService.findProject(id).get();
	if(project==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	if(token != null && token.getType().equals("invitation"))
	{
		User user=token.getUser();
		projectService.addMemberToProject(project,user);
		confirmationTokenRepository.delete(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<>("Please check the link !",HttpStatus.BAD_REQUEST);
}

@GetMapping(value="/declineInvitation")
public ResponseEntity<?> declineInvitation(@RequestParam("token")String confirmationToken){
	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	if(token != null && token.getType().equals("invitation"))
	{
		confirmationTokenRepository.delete(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<>("Please check the link !",HttpStatus.BAD_REQUEST);
}

@GetMapping(value="/getUsersImagesByProject/{id}")
public List<Resource>  getUsersImagesByProject(@PathVariable(value = "id") long id)
{
	return projectService.getUsersImagesByProject(id);
}



}
