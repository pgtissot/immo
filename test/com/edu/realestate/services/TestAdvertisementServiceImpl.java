package com.edu.realestate.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoJDBC;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.yelp.YelpResult;

public class TestAdvertisementServiceImpl {

	public static void main(String[] args) throws Exception {

		AdvertisementService as = new AdvertisementServiceImpl();

//		System.out.println(as.findAdvertisementById("8"));
//		System.out.println(as.findPictureById(13));
//
//		List<Picture> pads = as.findPictureByAdId(5335);
//		
//		for (Picture p : pads)
//			System.out.println(p);
		
//		CityDao cdao = new CityDaoJDBC();
//		City city = cdao.read(2003);
//		List<Advertisement> lads = as.findAdvertisements(city);

//		List<Advertisement> lads = as.findLatestAds(10);
//		for (Advertisement a : lads)
//			System.out.println(a.getRealEstate().getType());
//			System.out.println(a.getFrenchReleaseDate());
		
		CityDao cdao = new CityDaoJDBC();
		City city = cdao.read(1370);
		YelpResult yres = as.findYelpData(city);
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\test.txt"));
		writer.write(yres.toString());
		writer.close();

		
	}	
}
