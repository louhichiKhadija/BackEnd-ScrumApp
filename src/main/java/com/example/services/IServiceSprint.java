package com.example.services;

import java.util.Optional;

import com.example.entities.Sprint;
import com.example.entities.Taches;

public interface IServiceSprint {
	public void addSprint(Sprint sprint);
	public Optional<Sprint> getSprintById(int id);
	public void UpdateSprint(int id,Sprint sprint);
	public void deleteSprint(int id);
	public void addTaskToSprint(Sprint sprint, Taches task);
	public void startSprint(int id);
	
	
}
