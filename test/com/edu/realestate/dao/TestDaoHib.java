package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.*;

public class TestDaoHib {

	public static void main(String[] args) throws Exception {

//		CityDao cdao = new CityDaoHib();
//		City city = cdao.read(12);
//		System.out.println(city);
		
//		List<City> list = cdao.listMatching("Bord", false);
//		for (City c : list)
//			System.out.println(c);

//		List<City> list = cdao.listAll();
//		for (City c : list)
//			System.out.println(c);
//
//		UserDao udao = new UserDaoHib();
//		User user = udao.read("pgthebest@blah.fr");
//		System.out.println(user);
//
//		HouseDao hdao = new HouseDaoHib();
//		House house = hdao.read(52);
//		System.out.println(house);
//
//		ApartmentDao adao = new ApartmentDaoHib();
//		Apartment apartment = adao.read(48);
//		System.out.println(apartment);

//		AdvertisementDao ddao = new AdvertisementDaoHib();
//		Advertisement advertisement = ddao.read(2);
//		System.out.println(advertisement);
//		System.out.println(ddao.getLatest(5));
//		RealEstateDao rdao = new RealEstateDaoHib();
//		System.out.println("Rent : " + ddao.countRentAds() + " -- Sale : " + ddao.countSaleAds() + " -- Biens : " + rdao.countRealEstates());
		
//		PictureDao pdao = new PictureDaoHib();
//		System.out.println(pdao.getAllPicturesByAd(2));
		
		SearchDao sdao1 = new SearchDaoJDBC();
		SearchDao sdao2 = new SearchDaoHib();
		SearchCriteria sc = new SearchCriteria();
		sc.setRealEstateType(RealEstateType.Apartment);
		sc.setCityId(1370);
		System.out.println(sdao1.search(sc).size());
		System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
		System.out.println(sdao2.search(sc).size());
		
		
	}	
}
