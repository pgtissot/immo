package com.edu.realestate.services.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.City;
import com.edu.realestate.model.CommercialProperty;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.TransactionType;
import com.edu.realestate.services.AdvertisementService;

public class AdvertisementServiceMock implements AdvertisementService {

	@Override
	public List<Advertisement> findAdvertisements(City city) {
		List<Advertisement> adList = new ArrayList<>();

		RealEstate re = new CommercialProperty();
		Advertiser a = new Advertiser("Globubill", "1", "Seller", "Zorglub", "Biglodur", "0123456789");
		LocalDate ld = LocalDate.now();
		re.setCity(city);

		for (int i = 0; i < 10; i++) {
			Advertisement ad = new Advertisement(i, "blah", AdStatus.Pending, TransactionType.Sale, "",
					ld, "ADV-"+i, re, a, null, "");
			adList.add(ad);
		}
		Collections.shuffle(adList);

		return adList;
	}

	@Override
	public Advertisement findAdvertisementById(String id) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeAdvertisement(Advertisement ad) throws RealEstateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Picture findPictureById(int id) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Picture> findPictureByAdId(int aid) throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findLastestAds() throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> findBestAds() throws RealEstateException {
		// TODO Auto-generated method stub
		return null;
	}

}
