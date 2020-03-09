package com.example.services;

import java.util.Optional;

import com.example.entities.*;



public interface ServiceProject {

public Optional<Project> findProject(long id);
//public void addProject(Project project, int userId);
public void addproject(Project p);



public void addUserstory(Userstory u, long idProject);

public void addTache(Taches t, int idUserstrory);
}