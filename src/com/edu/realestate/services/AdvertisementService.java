package com.edu.realestate.services;

import java.util.List;
import java.util.Map;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.yelp.YelpResult;

public interface AdvertisementService {
		
	Advertisement findAdvertisementById(String id) throws RealEstateException;

	void placeAdvertisement(Advertisement ad) throws RealEstateException;
	
	Picture findPictureById(int id) throws RealEstateException;
	
	List<Picture> findPictureByAdId(int aid) throws RealEstateException;

	List<Advertisement> findLatestAds(int number) throws RealEstateException;
	
	List<Advertisement> findBestAds() throws RealEstateException;

	List<Advertisement> findAdvertisements(City city);
	
	YelpResult findYelpData(City city) throws Exception;

	Map<String, Integer> getAdsData();
	
}
