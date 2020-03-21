package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.entities.Project;
@Repository
public interface ProjectReprository  extends JpaRepository<Project,	Long>{
    List<Project> findByUserId(int id);

}
