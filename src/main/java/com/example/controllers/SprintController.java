package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.entities.Project;
import com.example.entities.Sprint;
import com.example.services.IServiceSprint;
import com.example.services.ServiceProject;





@CrossOrigin(origins = "*")
@RequestMapping("/sprints")
@RestController
public class SprintController {
	
	@Autowired 
	private IServiceSprint sprintService;

	@Autowired
	private ServiceProject projectService;


	
	
	@GetMapping(value="/getSprint/{id}")
	public ResponseEntity<?> getSprint(@PathVariable(value = "id") int id){
		Sprint sprint=sprintService.getSprintById(id).get();
		if(sprint != null)
		return new ResponseEntity<>(sprint,HttpStatus.OK);
		else return new ResponseEntity<>("This sprint doesn't exist",HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value="/getSprintsByProject/{id}")
	public ResponseEntity<?> getSprintByProject(@PathVariable(value = "id") Long id){
		List<Sprint> sprints=sprintService.getSprintsByProjetId(id);
		return new ResponseEntity<>(sprints,HttpStatus.OK);
	}



	//add new sprint (to a project)
	@PostMapping(value="/addSprint")
	public int setSprint(@RequestBody Sprint sprint){
		
			return sprintService.addSprint(sprint);
		
		
	}
	
	@PostMapping(value="/updateSprint/{id}")
	public ResponseEntity<?> updateSprint(@PathVariable int id,@RequestBody Sprint sprint){
		Sprint old=sprintService.getSprintById(id).get();
		if(old== null) return new ResponseEntity<>("this sprint doesn't exist", HttpStatus.EXPECTATION_FAILED);
		sprintService.UpdateSprint(id, sprint);
		return new ResponseEntity<>("Sprint is upsated successfully", HttpStatus.OK);
	}
	
	@GetMapping(value="/deleteSprint/{id}")
	public ResponseEntity<?> deleteSprint(@PathVariable int id){
		sprintService.deleteSprint(id);
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@GetMapping(value="/addTaskToSprint/{id}/{idTask}")
	public boolean addTaskToSprint(@PathVariable int id, @PathVariable int idTask){
		sprintService.addTaskToSprint(id, idTask);
		return true;
	}

	/*@GetMapping(value="/getCurrentSprint/{id}")
	public ResponseEntity<?> addTaskToSprint(@PathVariable Long id){
		Sprint sprint=sprintService.getCurrentSprint(id);
		if(sprint==null){
			Sprint firstSprint=sprintService.getSprintsByProjetId(id).get(0);
			sprintService.startSprint(firstSprint.getId());
			return new ResponseEntity<>(firstSprint.getTasks(),HttpStatus.OK);}
		
		return new ResponseEntity<>(sprintService.getCurrentSprint(id).getTasks(),HttpStatus.OK);	
	}*/
}
