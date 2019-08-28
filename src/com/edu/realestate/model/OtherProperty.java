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
	public String toFrench() {
		return "Autre";
	}

	public String toString() {
		return "Other Property " + super.toString();
	}


}
