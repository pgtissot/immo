package com.edu.realestate.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.edu.realestate.model.City;

public class Triage {

	public static void main(String[] args) {
		
		City c1 = new City (1, "Paris", "75000"); 
		City c2 = new City (2, "Dijon", "21000"); 
		City c3 = new City (3, "Toulouse", "31000"); 
		City c4 = new City (3, "Perpignan", "66000");
		City c5 = new City (5, "Clermont-Ferrand", "63000");
		City c6 = new City (1, "Nancy", "54000");

		Comparator cc = null;
		
		List cities = new ArrayList<City>();
		
		cities.add(c2);
		cities.add(c6);
		cities.add(c4);
		cities.add(c1);
		cities.add(c5);
		cities.add(c3);
		
		if (args.length > 0 && args[0].equals("1"))
			cc = new CityComparator();
		else if (args.length > 0 && args[0].equals("2"))
			cc = new CityComparatorCP();
		else
			cc = new CityComparatorShuffle();
			
		cities.sort(cc);
		//afficheCities(cities);
		
	}

	public static void afficheCities(List cities) {
		for (Object o : cities)
			System.out.println(o);
		System.out.println();
	}

}
