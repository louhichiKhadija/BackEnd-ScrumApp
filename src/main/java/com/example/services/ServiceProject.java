package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entities.Project;
import com.example.entities.User;

import org.springframework.core.io.Resource;



public interface ServiceProject {

public Optional<Project> findProject(long id);
public long addProject(Project project, String email);
public List<Project> getProjectsByUser(String email);
public boolean sendInvitation(Project project, String email);
public void addMemberToProject(Project project, User user);
public List<Resource> getUsersImagesByProject(long id);
public void deleteProject(long id);
}
