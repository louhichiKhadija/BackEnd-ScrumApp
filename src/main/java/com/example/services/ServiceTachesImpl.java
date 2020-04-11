package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;

import com.example.entities.Sprint;
import com.example.entities.Taches;
import com.example.repositories.SprintRepository;
import com.example.repositories.TacheReprository;

@Service
public class ServiceTachesImpl implements ServiceTaches {
 
	@Autowired
	private TacheReprository tacheReprository ;

	@Autowired
	private SprintRepository sprintRepository ;
	
	@Override 
	public void addTaches (Taches taches, int idSprint) {
		Sprint sprint = sprintRepository.findById(idSprint).get();
		List<Taches> tasks=sprint.getTasks();
		tasks.add(taches);
		sprint.setTasks(tasks);
		sprintRepository.save(sprint);
		
		taches.setSprint(sprint);
		tacheReprository.save(taches);
	}
	@Override 
	public List<Taches> getAll(){ 
		List<Taches> list=new ArrayList<>();
		tacheReprository.findAll().iterator().forEachRemaining(list::add);
      return list;
	}
	
	@Override
	public void updateTaches(Taches taches) {
		tacheReprository.saveAndFlush(taches);
	}
	
	@Override
	public List<Taches> getNonTakenTasks(){
		return tacheReprository.nonTakenTasks();
	}
	
	@Override
	public List<Taches> getTasksBySprint(int idSprint){
		return tacheReprository.findBySprintId(idSprint);
	}
}
