package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.entities.*;

import com.example.repositories.ProjectReprository;
import com.example.repositories.TacheReprository;
import com.example.repositories.UserRepository;
import com.example.repositories.UserstoryRepository;

@Service ("projectService")
public class ServiceProjectImpl implements ServiceProject {

	@Autowired 
	private ProjectReprository projectReprository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	UserstoryRepository userstoryRepository;
	
	@Autowired
	private TacheReprository tacheReprository;
	
	@Override
	public Optional<Project> findProject(long id) {
	
		return projectReprository.findById( id);
	}
	
	

	@Override
	public void addproject(Project p) {
		
		/*List<Taches> tasks = new ArrayList<Taches>();
		tasks.add(taches);
		userstory.setTaches(tasks);
		userstory.setProject(p);
		userstoryRepository.save(userstory);
		
		
		List<Userstory>userstories = new ArrayList<Userstory>();
		userstories.add(userstory);
		p.setUserStrories(userstories);*/
		projectReprository.save(p);
		
	}



	@Override
	public void addUserstory(Userstory u, long idProject ) {
		Project p = findProject(idProject).get();
		u.setProject(p);
		userstoryRepository.save(u);
	}



	@Override
	public void addTache(Taches t, int idUserstrory) {
		Userstory  story = userstoryRepository.findById(idUserstrory).get();
		t.setUserstory(story);
		tacheReprository.save(t);
		
	}
	
	
	

}
