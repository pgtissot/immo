package com.edu.es;

import java.util.List;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoES;
import com.edu.realestate.model.City;

public class TestES {

	public static void main(String[] args) {

		CityDao cdes = new CityDaoES();
		City city = cdes.read(15987);
//		System.out.println(city);
		
		List<City> list = cdes.listMatching("Paris", false);
		for (City c : list)
			System.out.println(c);
		
	}
}
