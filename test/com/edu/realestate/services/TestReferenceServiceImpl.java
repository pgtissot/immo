package com.edu.realestate.services;

import java.util.List;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoJDBC;
import com.edu.realestate.model.*;
import com.edu.realestate.services.ReferenceService;

public class TestReferenceServiceImpl {

	public static void main(String[] args) throws Exception {

		AdvertisementService as = new AdvertisementServiceImpl();
		ReferenceService rs = new ReferenceServiceImpl();
		Advertisement ad = as.findAdvertisementById("1792");

		SearchCriteria sc = new SearchCriteria();
//		int cityId = 12694;
//		CityDao cdao = new CityDaoJDBC();
//		City city = cdao.read(cityId);
		
		sc.setRealEstateType(RealEstateType.valueOf("House"));
//		sc.setCityId(cityId);
//		sc.setQuery("copropriété");
//		sc.setOffset(5);
		sc.setSort("distance DESC");
		sc.setLimit(10);
//		sc.setRoomsMin(2);
//		sc.setRoomsMax(2);
//		sc.setTransactionType(TransactionType.valueOf("Rent"));
//		sc.setLatitude(city.getLatitude());
//		sc.setLongitude(city.getLongitude());
//		sc.setDistance(200);
		
		List<Advertisement> lads = rs.findAdsByCriteria(sc);
		for (Advertisement a : lads)
			System.out.println(a);

//		List<City> cities = rs.findCitiesByName("75");
//		System.out.println(new ObjectMapper().writeValueAsString(cities));
		
		
	}	
}
