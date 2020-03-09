package com.example.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Userstory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)

	private Project project;
	
	 @OneToMany(fetch = FetchType.EAGER, mappedBy ="userstory")
	    List<Taches> taches = new ArrayList<Taches>();
 	
	
	
	public Userstory() {
		
	}
	
	public Userstory(String name) {
		this.name = name;
	}



	public Userstory(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	public List<Taches> getTaches() {
		return taches;
	}



	public void setTaches(List<Taches> taches) {
		this.taches = taches;
	}
	
	
	
	
	
	
}
