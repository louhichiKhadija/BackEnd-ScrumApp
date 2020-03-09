package com.example.entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	  @NotNull
	    @Lob
	    private String name;
	  
	  	private String description;
	  		
	  
	    @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "project_user",
	    joinColumns = { @JoinColumn(name = "project_id",referencedColumnName = "id") },
	    inverseJoinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id") })
	    private List<User> user = new ArrayList<>();
	    
	    @OneToMany(fetch = FetchType.EAGER, mappedBy ="project")
	    List<Userstory> userStrories = new ArrayList<Userstory>();
	    
	    
	    public Project() {}
	    
	    public Project(String name, String description) {
	    	this.name = name;
	    	this.description = description;
	    	}
	    

		public Project(long id, @NotNull String name, List<User> user) {
			super();
			this.id = id;
			this.name = name;
			this.user = user;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<User> getUser() {
			return user;
		}

		public void setUser(List<User> users) {
			this.user = users;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Userstory> getUserStrories() {
			return userStrories;
		}

		public void setUserStrories(List<Userstory> userStrories) {
			this.userStrories = userStrories;
		}
		
		
		
	    
	    
}
