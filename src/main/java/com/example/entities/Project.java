package com.example.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	  @NotNull
	    @Lob
	    private String name;
	  
	    @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "project_user",
	    joinColumns = { @JoinColumn(name = "project_id",referencedColumnName = "id") },
	    inverseJoinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id") })
	    private Set<User> user = new HashSet<>();
	    
	    
	    public Project() {}
	    

		public Project(long id, @NotNull String name, Set<User> user) {
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

		public Set<User> getUser() {
			return user;
		}

		public void setUser(Set<User> user) {
			this.user = user;
		}
	    
	    
}
