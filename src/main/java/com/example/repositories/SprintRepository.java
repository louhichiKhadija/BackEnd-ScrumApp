package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.entities.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
    List<Sprint> findByProjectId(int projectId);

}
