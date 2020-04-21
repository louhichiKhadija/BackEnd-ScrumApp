package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.UserStory;
import com.example.services.ServiceUserStory;

@CrossOrigin(origins = "*")
@RequestMapping("/userStory")
@RestController
public class UserStoryController{

    @Autowired
    private ServiceUserStory userStoryService;

    @PostMapping("/addUserStory/{idProject}")
    public int addUserStory(@RequestBody UserStory userStory, @PathVariable long idProject){
        return userStoryService.save(userStory, idProject);
    }

}