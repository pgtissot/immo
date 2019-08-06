package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.Advertisement;

public interface AdvertisementDao extends AbstractDao<Advertisement> {

	List<Advertisement> getLatest(int number);

	int countSaleAds();

	int countRentAds();

}
