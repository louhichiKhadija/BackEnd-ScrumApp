package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entities.Project;
import com.example.entities.User;

import org.springframework.core.io.Resource;



public interface ServiceProject {

public Optional<Project> findProject(long id);
public void addProject(Project project);
public List<Project> getProjectsByUser();
public boolean sendInvitation(Project project, String email);
public void addMemberToProject(Project project, User user);
public List<Resource> getUsersImagesByProject(long id);
}
