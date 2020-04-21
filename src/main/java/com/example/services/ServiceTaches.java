package com.example.services;

import java.util.List;

import com.example.entities.Taches;

public interface ServiceTaches {
public List<Taches> getAll();
public Taches addTaches(Taches T, int idSprint);
public void updateTaches( Taches taches);
public List<Taches> getNonTakenTasks(long id);
public List<Taches> getTasksBySprint(int idSprint);
public void add(Taches task, int idUserStory);
public void addOwner(int id, String email);
	
}
