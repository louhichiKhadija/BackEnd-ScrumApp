package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;

import com.example.entities.Sprint;
import com.example.entities.Taches;
import com.example.entities.User;
import com.example.entities.UserStory;
import com.example.repositories.UserStoryRepository;
import com.example.repositories.SprintRepository;
import com.example.repositories.TacheReprository;
import com.example.repositories.UserRepository;

@Service
public class ServiceTachesImpl implements ServiceTaches {
 
	@Autowired
	private TacheReprository tacheReprository ;

	@Autowired
	private SprintRepository sprintRepository ;

	@Autowired
	private UserStoryRepository userStoryRepository ;

	@Autowired
	private UserRepository userRepository;

	
	@Override 
	public Taches addTaches (Taches taches, int idSprint) {
		Sprint sprint = sprintRepository.findById(idSprint).get();
		List<Taches> tasks=sprint.getTasks();
		tasks.add(taches);
		sprint.setTasks(tasks);
		sprintRepository.save(sprint);
		
		taches.setSprint(sprint);
		taches=tacheReprository.save(taches);
		return taches;
	}
	@Override 
	public List<Taches> getAll(){ 
		List<Taches> list=new ArrayList<>();
		tacheReprository.findAll().iterator().forEachRemaining(list::add);
      return list;
	}
	
	@Override
	public void updateTaches(Taches taches) {
		Taches oldTache=tacheReprository.findById(taches.getId()).get();
		oldTache.setState(taches.getState());
		tacheReprository.saveAndFlush(oldTache);
	}
	
	@Override
	public List<Taches> getNonTakenTasks(long id){
		List<UserStory> userStories=userStoryRepository.findByProjectId(id);
		List<Taches> tasks=tacheReprository.nonTakenTasks();
		List<Taches> nonTakenTasks=new ArrayList<Taches>();
		userStories.forEach(us->{
			tasks.forEach(task->{
				if(us.getTaches().contains(task) && task.getSprint()==null)
				nonTakenTasks.add(task);
			});
		});

		return nonTakenTasks;
	}
	
	@Override
	public List<Taches> getTasksBySprint(int idSprint){
		return tacheReprository.findBySprintId(idSprint);
	}

	@Override
	public void add(Taches task, int idUserStory){
		UserStory userStory= userStoryRepository.findById(idUserStory).get();
		task.setUserStory(userStory);
		Taches t= tacheReprository.save(task);
		List<Taches> tasks=userStory.getTaches();
		tasks.add(t);
		userStory.setTaches(tasks);
		userStoryRepository.save(userStory);

	
	}

	@Override
	public void addOwner(int id, String email){
		Taches tache=tacheReprository.findById(id).get();
		User user=userRepository.findByEmail(email);
		tache.setOwner(user);
		tacheReprository.save(tache);
	}
}
