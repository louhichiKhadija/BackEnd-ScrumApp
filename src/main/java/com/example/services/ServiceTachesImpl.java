package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;

import com.example.entities.Taches;
import com.example.repositories.TacheReprository;

@Service
public class ServiceTachesImpl implements ServiceTaches {
 
	@Autowired
	private TacheReprository tacheReprository ;
	
	@Override 
	public void addTaches (Taches taches) {
		tacheReprository.save(taches);
	}
	@Override 
	public List<Taches> getAll(){ 
		List<Taches> list=new ArrayList<>();
		tacheReprository.findAll().iterator().forEachRemaining(list::add);
      return list;
	}
	
	@Override
	public void updateTaches(Taches taches) {
		tacheReprository.saveAndFlush(taches);
	}
	@Override
	public void deleteTache(long id) {
		tacheReprository.deleteById(id);
	}
}
