package com.edu.realestate.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="commercial_property")
@PrimaryKeyJoinColumn(name="id")
public class CommercialProperty extends RealEstate {

	public CommercialProperty() {}

	@Override
	public String getType() {
		return "Commercial";
	}

	@Override
	public String toFrench() {
		return "Local Commercial";
	}

	public String toString() {
		return "Commercial Property [" + super.toString() + "]";
	}
}
