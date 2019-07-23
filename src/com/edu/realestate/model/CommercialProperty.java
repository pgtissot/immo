package com.edu.realestate.model;

public class CommercialProperty extends RealEstate {

	public CommercialProperty() {}

	public CommercialProperty(int id) {
		super(id);
	}

	public String toString() {
		return "Commercial Property [" + super.toString() + "]";
	}
}
