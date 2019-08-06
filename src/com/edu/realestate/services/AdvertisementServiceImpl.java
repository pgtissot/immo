package com.edu.realestate.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.realestate.dao.AdvertisementDao;
import com.edu.realestate.dao.AdvertisementDaoJDBC;
import com.edu.realestate.dao.PictureDao;
import com.edu.realestate.dao.PictureDaoJDBC;
import com.edu.realestate.dao.RealEstateDao;
import com.edu.realestate.dao.RealEstateDaoJDBC;
import com.edu.realestate.dao.SearchDao;
import com.edu.realestate.dao.SearchDaoJDBC;
import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.SearchCriteria;
import com.edu.realestate.yelp.YelpResult;
import com.edu.realestate.yelp.YelpSearch;

public class AdvertisementServiceImpl implements AdvertisementService {

	SearchDao sdao;
	AdvertisementDao adao;
	PictureDao pdao;
	RealEstateDao rdao;
	
	
	public AdvertisementServiceImpl() {
		adao = new AdvertisementDaoJDBC();
		pdao = new PictureDaoJDBC();
		rdao = new RealEstateDaoJDBC();
		sdao = new SearchDaoJDBC();
	}

	@Override
	public Advertisement findAdvertisementById(String id) throws RealEstateException {
		return adao.read(Integer.parseInt(id)); 
	}

	@Override
	public void placeAdvertisement(Advertisement ad) throws RealEstateException {
		adao.create(ad);
	}

	@Override
	public Picture findPictureById(int id) throws RealEstateException {
		return pdao.read(id); 
	}

	@Override
	public List<Picture> findPictureByAdId(int aid) throws RealEstateException {
		return pdao.getAllPicturesByAd(aid); 
	}

	@Override
	public List<Advertisement> findLatestAds(int number) throws RealEstateException {
		return adao.getLatest(number);
	}

	@Override
	public List<Advertisement> findBestAds() throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findAdvertisements(City city) {
		SearchCriteria sc = new SearchCriteria();
		sc.setCityId(city.getId());
		return sdao.search(sc);
	}

	@Override
	public YelpResult findYelpData(City city) throws Exception {
		return new YelpResult(YelpSearch.getBusinesses(city), YelpSearch.getEvents(city));
	}

	public Map<String, Integer> getAdsData() {
		Map<String, Integer> data = new HashMap<>();
		
		data.put("Rent", adao.countRentAds());
		data.put("Sale", adao.countSaleAds());
		data.put("RealEstate", rdao.countRealEstates());
		
		return data;
	}


}
