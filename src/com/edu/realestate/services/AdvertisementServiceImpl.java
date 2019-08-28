package com.edu.realestate.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.realestate.dao.AdvertisementDao;
import com.edu.realestate.dao.PictureDao;
import com.edu.realestate.dao.RealEstateDao;
import com.edu.realestate.dao.SearchDao;
import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.yelp.YelpResult;
import com.edu.realestate.yelp.YelpSearch;

@Service
@Transactional(readOnly=true)
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	SearchDao sdao;

	@Autowired
	AdvertisementDao adao;

	@Autowired
	PictureDao pdao;

	@Autowired
	RealEstateDao rdao;

	@Override
	public Advertisement findAdvertisementById(int id) throws RealEstateException {
		return adao.read(id);
	}

	@Override
	public Advertisement findAdvertisementByNumber(String adNumber) throws RealEstateException {
		return adao.findAdvertisementByNumber(adNumber);
	}

	@Override
	@Transactional(readOnly=false)
	public void placeAdvertisement(Advertisement ad) throws RealEstateException {
		adao.create(ad);
	}

	@Override
	public Picture findPictureById(int id) throws RealEstateException {
		return pdao.read(id);
	}

	@Override
	public List<Picture> findPicturesByAdId(int aid) throws RealEstateException {
		return pdao.getAllPicturesByAd(aid);
	}

	@Override
	public List<Advertisement> findLatestAds(int number) throws RealEstateException {
		return adao.getLatest(number);
	}

	@Override
	public void validateAdvertisement(int adId) throws RealEstateException {
		adao.validateAdvertisement(adId);
	}

	@Override
	public void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException {
		adao.refuseAdvertisement(adId, refusedComment);
		
	}

	@Override
	public List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException {
		return adao.findAdvertisementsByStatus(status);
	}


	@Override
	public YelpResult findYelpData(City city) throws Exception {
		return new YelpResult(YelpSearch.getBusinesses(city), YelpSearch.getEvents(city));
	}

	public Map<String, Long> getAdvertisementsData() {
		Map<String, Long> adsData = new HashMap<>();

		adsData.put("Rent", adao.countRentAds());
		adsData.put("Sale", adao.countSaleAds());
		adsData.put("RealEstate", rdao.countRealEstates());
		
		return adsData;

	}

}
