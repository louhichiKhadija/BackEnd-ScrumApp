package com.example.services;

import java.util.List;

import com.example.entities.Taches;

public interface ServiceTaches {
public List<Taches> getAll();
public void addTaches(Taches T);
public void updateTaches( Taches taches);	
	
}
