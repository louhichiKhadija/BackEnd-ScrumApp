package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entities.Sprint;


public interface IServiceSprint {
	public int addSprint(Sprint sprint);
	public Optional<Sprint> getSprintById(int id);
	public void UpdateSprint(int id,Sprint sprint);
	public void deleteSprint(int id);
	public void addTaskToSprint(int id, int idTask);
	public void startSprint(int id);
	public List<Sprint> getSprintsByProjetId(Long projectId);
	//public Sprint getCurrentSprint(Long id);
	
}
