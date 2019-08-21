package com.edu.realestate.services;

import com.edu.realestate.dao.AdvertisementDao;
import com.edu.realestate.dao.AdvertisementDaoJDBC;
import com.edu.realestate.dao.FavoriteDao;
import com.edu.realestate.dao.FavoriteDaoJDBC;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Favorite;

public class FavoriteServiceImpl implements FavoriteService {

	AdvertisementDao adao;
	FavoriteDao fdao;
	
	public FavoriteServiceImpl() {
		adao = new AdvertisementDaoJDBC();
		fdao = new FavoriteDaoJDBC();
	}
	
	@Override
	public void addFavAds(String username, int advertisementId, int priority, String comments) {
		Advertisement ad = adao.read(advertisementId);
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
	public void removeFavAds(Integer id) {
		fdao.delete(fdao.read(id));
	}

}
