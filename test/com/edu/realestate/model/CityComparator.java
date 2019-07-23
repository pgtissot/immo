package com.edu.realestate.model;

import java.util.Comparator;

import com.edu.realestate.model.City;

public class CityComparator implements Comparator<City> {

	@Override
	public int compare(City c1, City c2) {
		if (c1.getId() != c2.getId())
			return c1.getId() - c2.getId();
		return c1.getName().compareTo(c2.getName());
	}

}
