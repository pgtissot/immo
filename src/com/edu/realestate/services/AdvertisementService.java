package com.edu.realestate.services;

import java.util.List;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;

public interface AdvertisementService {
		
	Advertisement findAdvertisementById(String id) throws RealEstateException;

	void placeAdvertisement(Advertisement ad) throws RealEstateException;
	
	Picture findPictureById(int id) throws RealEstateException;
	
	List<Picture> findPictureByAdId(int aid) throws RealEstateException;

	List<Advertisement> findLastestAds() throws RealEstateException;
	
	List<Advertisement> findBestAds() throws RealEstateException;

	List<Advertisement> findAdvertisements(City city);

	
}
