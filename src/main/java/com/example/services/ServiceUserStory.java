package com.example.services;

import com.example.entities.UserStory;

public interface ServiceUserStory{

    public int save(UserStory userStory, long idProject);
}