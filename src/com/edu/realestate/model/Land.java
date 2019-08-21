package com.edu.realestate.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Land extends RealEstate {

	public Land() {}

	@Override
	public String getType() {
		return "Land";
	}

	@Override
	public String toFrench() {
		return "Terrain";
	}

	@Override
	public String toString() {
		return "Land [" + super.toString() + "]";
	}
	

}
