package com.edu.realestate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Moderator extends User {
	
	private String title;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String phone;

	private String name;

	public Moderator() {}

	public Moderator(String username, String password, String title, String firstName, String lastName, String phone, String name) {
		super(username, password);
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Moderator [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", name=" + name + "]";
	}


}
