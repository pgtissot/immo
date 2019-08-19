package com.edu.realestate.model;

public class Favorite {

	private int id;
	private String username;
	private Advertisement ad;
	private int priority;
	private String comments;

	public Favorite(int id, String username, Advertisement ad, int priority, String comments) {
		this.id = id;
		this.username = username;
		this.ad = ad;
		this.priority = priority;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Advertisement getAd() {
		return ad;
	}

	public void setAd(Advertisement ad) {
		this.ad = ad;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", username=" + username + ", ad=" + ad + ", priority=" + priority + ", comments="
				+ comments + "]";
	}
	
	
	
}
