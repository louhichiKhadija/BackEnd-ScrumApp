package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.entities.Taches;

@Repository
public interface TacheReprository  extends JpaRepository<Taches,Long>{
	List<Taches> findTachesBySprintId(int id);

}
