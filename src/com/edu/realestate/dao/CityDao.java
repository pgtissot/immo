package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.City;

public interface CityDao extends AbstractDao<City> {

	List<City> listMatching(String comparator);
	
	List<City> listAll();
}
