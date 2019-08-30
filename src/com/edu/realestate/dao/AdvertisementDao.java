package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;

public interface AdvertisementDao extends AbstractDao<Advertisement> {

	Advertisement readAllStatus(Integer id);
	
	Advertisement findAdvertisementByNumber(String name);
	
	List<Advertisement> findAdvertisementByCity(int cityId);

	void validateAdvertisement(int adId) throws RealEstateException;
	
	void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException;

	List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException;

	List<Advertisement> getLatest(int number);

	List<String> listMatching(String comparator, boolean exact);
	
	long countSaleAds();

	long countRentAds();

}
