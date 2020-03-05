package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entities.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{

}
