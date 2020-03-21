package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.entities.Taches;

@Repository
public interface TacheReprository  extends JpaRepository<Taches,Integer>{
	List<Taches> findTachesBySprintId(int id);
	
	@Query("select t from Taches t where t.sprint IS NULL")
	List<Taches> nonTakenTasks();

	List<Taches> findBySprintId(int id);

}
