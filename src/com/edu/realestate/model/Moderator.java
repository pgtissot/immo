package com.edu.realestate.model;

public class Moderator extends User {
	
	private String name;

	public Moderator() {}
	
	public Moderator(String username, String password, String name) {
		super(username, password);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Moderator [name=" + name + "] " + super.toString();
	}
	
}
