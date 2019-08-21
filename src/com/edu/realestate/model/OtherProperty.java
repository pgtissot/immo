package com.edu.realestate.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="other_property")
@PrimaryKeyJoinColumn(name="id")
public class OtherProperty extends RealEstate {

	public OtherProperty() {}

	@Override
	public String getType() {
		return "Other";
	}

	@Override
	public String toFrench() {
		return "Appartement";
	}

	public String toString() {
		return "Other Property " + super.toString();
	}


}
