package com.edu.realestate.services.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.SearchCriteria;
import com.edu.realestate.services.ReferenceService;

public class ReferenceServiceMock implements ReferenceService {
	
	@Override
	public List<City> listCities() {
		List<City> cities = new ArrayList<>();
		City c1 = new City(1, "Paris", "75000");
		City c2 = new City(2, "Bordeaux", "33000");
		City c3 = new City(3, "Toulouse", "31000");
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);
		return cities;
	}

	@Override
	public List<City> findCitiesByName(String input) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findAdsByCriteria(SearchCriteria criteria) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getAdsData() {
		// TODO Auto-generated method stub
		return null;
	}

}
