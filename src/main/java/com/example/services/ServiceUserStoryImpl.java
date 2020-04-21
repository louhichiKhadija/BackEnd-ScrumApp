package com.example.services;

import java.util.List;

import com.example.entities.Project;
import com.example.entities.UserStory;
import com.example.repositories.ProjectReprository;
import com.example.repositories.UserStoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserStoryImpl implements ServiceUserStory{

    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private ProjectReprository projectRepository;



    @Override
    public int save(UserStory userStory, long idProject){
       Project project=projectRepository.findById(idProject).get();
        userStory.setProject(project);
        UserStory us=userStoryRepository.save(userStory);
        List<UserStory> userStories=project.getUserStories();
        userStories.add(us);
        project.setUserStories(userStories);

        projectRepository.save(project);

        return us.getId();

    }
    


}