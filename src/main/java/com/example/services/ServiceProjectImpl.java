package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.entities.ConfirmationToken;
import com.example.entities.Project;
import com.example.entities.User;
import com.example.repositories.ConfirmationTokenRepository;
import com.example.repositories.ProjectReprository;
import com.example.repositories.UserRepository;
import com.example.utils.FileStorageService;
import com.example.utils.MailService;

@Service ("projectService")
public class ServiceProjectImpl implements ServiceProject {

	@Autowired 
	private ProjectReprository projectReprository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository; 
	
	@Autowired
	private MailService mailService;

	@Autowired
	private IServiceUser userService;

	@Autowired
	private FileStorageService fs;
	
	@Override
	public Optional<Project> findProject(long id) {
	
		return projectReprository.findById( id);
	}
	
	@Override
	public void addProject(Project project) {
		String email= SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		Set<User> users=project.getUser();
		users.add(user);
		project.setUser(users);
		projectReprository.save(project);
	}
	
	@Override
	public List<Project> getProjectsByUser(){
		String email= SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		if(user !=null)
		return projectReprository.findByUserId(user.getId());
		else return null;
	}

	@Override
	public boolean sendInvitation(Project project, String email){
		User user=userRepository.findByEmail(email);
		if(user==null) return false;
		
		ConfirmationToken confirmationToken=new ConfirmationToken(user, "invitation");
		confirmationTokenRepository.save(confirmationToken);
		
		mailService.sendInvitationEmail(user, project, confirmationToken);
		return true;
	}

	@Override
	public void addMemberToProject(Project project, User user){
		Set<User> users=project.getUser();
		users.add(user);
		project.setUser(users);
		projectReprository.save(project);
	}

	@Override
	public List<Resource> getUsersImagesByProject(long id){
		Project project= projectReprository.findById(id).get();
		Set<User> users=project.getUser();
		List<Resource> listImages= new ArrayList<>();
		List<User> list = new ArrayList<>();
		users.iterator().forEachRemaining(list::add);

		users.forEach(user ->{
			try{
				System.out.println(user.getEmail());
			String fileName = userService.getFileName(user.getId());
			listImages.add(fs.loadFileAsResource(fileName));}
			catch(Exception ex){}
		});

		return listImages;
	}

}
