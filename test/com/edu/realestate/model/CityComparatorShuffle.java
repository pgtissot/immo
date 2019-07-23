package com.edu.realestate.model;

import java.util.Comparator;

import com.edu.realestate.model.City;

public class CityComparatorShuffle implements Comparator<City> {

	@Override
	public int compare(City c1, City c2) {
		return Math.random() < 0.5 ? -1 : Math.random() > 0.5 ? 1 : 0;
	}

}
