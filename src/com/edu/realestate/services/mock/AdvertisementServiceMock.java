package com.edu.realestate.services.mock;

import java.util.List;
import java.util.Map;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.AdvertisementModel;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.services.AdvertisementService;
import com.edu.realestate.yelp.YelpResult;

public class AdvertisementServiceMock implements AdvertisementService {

	@Override
	public Advertisement findAdvertisementById(int id) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertisement findAdvertisementByNumber(String adNumber) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeAdvertisement(Advertisement ad) throws RealEstateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Picture> findPicturesByAdId(int aid) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findLatestAds(int number) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateAdvertisement(int adId) throws RealEstateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YelpResult findYelpData(City city) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Long> getAdvertisementsData() throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture findPictureById(int id) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAdNumbers(String input, boolean exact) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertisement createAdFromModel(AdvertisementModel am) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findAdvertisementByCity(int cityId) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

}
