package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Taches;
import com.example.services.ServiceTaches;
@CrossOrigin(origins = "*")
@RequestMapping("/taches")
@RestController
public class TachesController {
	@Autowired 
	private ServiceTaches serviceTaches;
	
	
	@GetMapping(value="/taches")
	public List<Taches> getAll(){
		return serviceTaches.getAll();
	}
	

@PostMapping(value="/add")
public void adTaches(@RequestBody  Taches taches) {
	serviceTaches.addTaches(taches);
}
@PostMapping(value="/update")
public void update(@RequestBody Taches taches) {
	
	serviceTaches.updateTaches(taches);
}
@PostMapping(value="/delete/{id}")
public void deleteTache(@PathVariable(name="id") Long id) {
	
	serviceTaches.deleteTache(id);
}
}
