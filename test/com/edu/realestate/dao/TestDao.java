package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.*;

public class TestDao {

	public static void main(String[] args) throws Exception {

		CityDao cdao = new CityDaoJDBC();
		City city = cdao.read(12);
//		System.out.println(city);
		
		List<City> list = cdao.listAll();
//		for (City c : list)
//			System.out.println(c);

		UserDao udao = new UserDaoJDBC();
		User user = udao.read("pgt");
//		System.out.println(user);

		HouseDao hdao = new HouseDaoJDBC();
		House house = hdao.read(52);
//		System.out.println(house);

		ApartmentDao adao = new ApartmentDaoJDBC();
		Apartment apartment = adao.read(48);
//		System.out.println(apartment);
		
		AdvertisementDao ddao = new AdvertisementDaoJDBC();
		Advertisement advertisement = ddao.read(2);
//		System.out.println(advertisement);
		
	}	
}
