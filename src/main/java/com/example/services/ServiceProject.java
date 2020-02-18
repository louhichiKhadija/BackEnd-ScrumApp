package com.example.services;

import java.util.Optional;

import com.example.entities.Project;
import com.example.entities.User;



public interface ServiceProject {

public Optional<Project> findProject(long id);
public void addProject(Project project, User user);
}
