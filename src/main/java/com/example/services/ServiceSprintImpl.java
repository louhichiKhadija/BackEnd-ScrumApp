package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Sprint;
import com.example.entities.Taches;
import com.example.repositories.SprintRepository;
import com.example.repositories.TacheReprository;


@Service
public class ServiceSprintImpl implements IServiceSprint{
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private TacheReprository tacheRepository;
	
	
	@Override
	public int addSprint(Sprint sprint) {
		sprint=sprintRepository.save(sprint);
		return sprint.getId();
	}

	
	@Override
	public Optional<Sprint> getSprintById(int id) {
		return sprintRepository.findById(id);}
	
	@Override
	public void UpdateSprint(int id,Sprint sprint) {
		Sprint oldSprint= sprintRepository.findById(id).get();
		if(oldSprint != null) {
			oldSprint.setName(sprint.getName());
			oldSprint.setTasks(sprint.getTasks());
			oldSprint.setCurrent(sprint.isCurrent());
			oldSprint.setDescription(sprint.getDescription());
			sprintRepository.save(oldSprint);
		}
		
	}
	
	@Override
	public void deleteSprint(int id) {
		List<Taches> taches = tacheRepository.findTachesBySprintId(id);
		taches.forEach(task -> {
			task.setSprint(null);
			tacheRepository.save(task);
		});
		
		sprintRepository.deleteById(id);
	}
	
	@Override
	public void addTaskToSprint(int id, int idTask){
		Sprint sprint=sprintRepository.findById(id).get();
		Taches task= tacheRepository.findById(idTask).get();
		List<Taches> tasks= sprint.getTasks();
		if(!(tasks.contains(task))) {
			tasks.add(task);
			task.setSprint(sprint);
			tacheRepository.save(task);
			
			sprint.setTasks(tasks);
			sprintRepository.save(sprint);}
	}
	
	@Override
	public void startSprint(int id) {
		Sprint sprint=sprintRepository.findById(id).get();
		
		if(sprint != null) {
			sprint.setCurrent(true);
			sprintRepository.save(sprint);
		}
	}
	
	@Override
	public List<Sprint> getSprintsByProjetId(int projectId){
		return sprintRepository.findByProjectId(projectId);
	}

}
