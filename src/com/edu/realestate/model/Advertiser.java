package com.edu.realestate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Advertiser extends User {

	private String title;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String phone;

	public Advertiser() {}

	public Advertiser(String username, String password, String title, String firstName, String lastName, String phone) {
		super(username, password);
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "Advertiser [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ "] " + super.toString();
	}

}
