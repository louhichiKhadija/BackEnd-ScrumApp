package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy ="userStory")
    private List<Taches> taches=new ArrayList<Taches>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

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

    public List<Taches> getTaches() {
        return taches;
    }

    public void setTaches(List<Taches> taches) {
        this.taches = taches;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    

}