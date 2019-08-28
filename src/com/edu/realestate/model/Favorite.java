package com.edu.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="favoris")
public class Favorite {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="owner")
	private String username;

	@ManyToOne
	@JoinColumn(name="advertisement_id")
	private Advertisement advertisement;

	private int priority;
	private String comments;
	
	public Favorite() {
	}

	public Favorite(int id, String username, Advertisement ad, int priority, String comments) {
		this.id = id;
		this.username = username;
		this.advertisement = ad;
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
		return advertisement;
	}

	public void setAd(Advertisement ad) {
		this.advertisement = ad;
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
		return "Favorite [id=" + id + ", user=" + username + ", ad=" + advertisement + ", priority=" + priority + ", comments="
				+ comments + "]";
	}
	
	
	
}
