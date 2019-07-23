package com.edu.realestate.model;

import java.util.Comparator;

import com.edu.realestate.model.City;

public class CityComparatorCP implements Comparator<City> {

	@Override
	public int compare(City c1, City c2) {
		return c1.getPostcode().compareTo(c2.getPostcode());
	}

}
