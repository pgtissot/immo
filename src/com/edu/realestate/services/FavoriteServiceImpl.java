package com.edu.realestate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.realestate.dao.AdvertisementDao;
import com.edu.realestate.dao.FavoriteDao;
import com.edu.realestate.dao.UserDao;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Favorite;
import com.edu.realestate.model.User;

@Service
@Transactional(readOnly=true)
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	AdvertisementDao adao;

	@Autowired
	FavoriteDao fdao;
	
	@Autowired
	UserDao udao;

	@Override
	@Transactional(readOnly=false)
	public void addFavAds(String username, int advertisementId, int priority, String comments) {
		Advertisement ad = adao.read(advertisementId);
		User u = udao.read(username);
		Favorite favorite = new Favorite(0, username, ad, priority, comments);
		fdao.create(favorite);
	}

	@Override
	public Favorite getFavByAdId(String username, int advertisementId) {
		return fdao.read(username, advertisementId);
	}
	
	
	@Override
	public boolean isFavAd(String username, int advertisementId) {
		return fdao.isFavAd(username, advertisementId);
	}

	@Override
	@Transactional(readOnly=false)
	public void removeFavAd(Integer id) {
		fdao.delete(fdao.read(id));
	}

}
