package com.edu.realestate.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Parking extends RealEstate {

	public Parking() {}
	
	@Override
	public String toFrench() {
		return "Parking";
	}

	@Override
	public String toString() {
		return "Parking [" + super.toString() + "]";
	}


}

