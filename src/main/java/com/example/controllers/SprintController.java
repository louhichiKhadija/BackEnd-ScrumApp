package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Sprint;
import com.example.services.IServiceSprint;

@CrossOrigin(origins = "*")
@RestController
public class SprintController {
	
	@Autowired 
	private IServiceSprint sprintService;
	
	@GetMapping(value="/getSprint/{id}")
	public ResponseEntity<?> getSprint(@PathVariable(value = "id") int id){
		Sprint sprint=sprintService.getSprintById(id).get();
		if(sprint != null)
		return new ResponseEntity<>(sprint,HttpStatus.OK);
		else return new ResponseEntity<>("This sprint doesn't exist",HttpStatus.BAD_REQUEST);
	}

}
