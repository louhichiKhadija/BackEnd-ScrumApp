package com.example.entities;

import javax.persistence.*;


@Entity
public class Taches {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title ;
	private String content;
	private String state ;
	
	
	
	
	public Taches(int id, String title, String content, String state) {
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.state = state;
	}




	public Taches() {
		
		
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}
	

}
