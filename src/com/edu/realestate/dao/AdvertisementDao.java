package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;

public interface AdvertisementDao extends AbstractDao<Advertisement> {

	Advertisement findAdvertisementByNumber(String name);

	void validateAdvertisement(int adId) throws RealEstateException;
	
	void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException;

	List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException;

	List<Advertisement> getLatest(int number);

	long countSaleAds();

	long countRentAds();

}
