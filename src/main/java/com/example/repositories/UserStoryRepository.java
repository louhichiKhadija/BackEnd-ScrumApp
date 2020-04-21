package com.example.repositories;

import java.util.List;

import com.example.entities.UserStory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Integer>{
    List<UserStory> findByProjectId(long id);
}