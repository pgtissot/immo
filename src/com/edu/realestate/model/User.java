package com.edu.realestate.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="user_data")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType=DiscriminatorType.STRING)
public abstract class User {

	@Id
	private String username;
	private String password;
	
//	@OneToMany (mappedBy="user")
//	private List<Favorite> favorites;

	public User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
//		this.favorites = new ArrayList<>();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<Favorite> getFavorites() {
//		return favorites;
//	}
//
//	public void setFavorites(List<Favorite> favorites) {
//		this.favorites = favorites;
//	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
//		return "User [username=" + username + ", password=" + password + ", favorites=" + favorites + "]";
	}


	
}
