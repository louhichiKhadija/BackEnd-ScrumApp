package com.example.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Userstory;

@Repository
public interface UserstoryRepository extends JpaRepository<Userstory, Integer>{

}
