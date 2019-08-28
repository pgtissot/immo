package com.edu.realestate.services;

import java.util.List;
import java.util.Map;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.yelp.YelpResult;

public interface AdvertisementService {

	Advertisement findAdvertisementById(int id) throws RealEstateException;

	Advertisement findAdvertisementByNumber(String adNumber) throws RealEstateException;

	void placeAdvertisement(Advertisement ad) throws RealEstateException;

	Picture findPictureById(int id) throws RealEstateException;

	List<Picture> findPicturesByAdId(int aid) throws RealEstateException;

	List<Advertisement> findLatestAds(int number) throws RealEstateException;

	void validateAdvertisement(int adId) throws RealEstateException;

	void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException;

	List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException;

	YelpResult findYelpData(City city) throws Exception;

	Map<String, Long> getAdvertisementsData() throws RealEstateException;

}
