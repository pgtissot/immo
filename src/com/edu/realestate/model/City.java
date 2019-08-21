package com.edu.realestate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;
	private String postcode;
	private Double longitude;
	private Double latitude;

	public City() {}
	
	public City(int id, String name, String postcode) {
		this.id = id;
		this.name = name;
		this.postcode = postcode;
	}
	
	public City(int id, String name, String postcode, double longitude, double latitude) {
		this.id = id;
		this.name = name;
		this.postcode = postcode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getFullName() {
		return name + " (" + postcode + ")";
	}


	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", postcode=" + postcode + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}

}
