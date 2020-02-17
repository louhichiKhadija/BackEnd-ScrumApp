package com.example.services;

import java.util.Optional;

import com.example.entities.Project;



public interface ServiceProject {

public Optional<Project> findProject(long id);
public void addProject(Project project);
}
