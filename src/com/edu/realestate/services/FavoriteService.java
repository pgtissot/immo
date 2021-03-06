package com.edu.realestate.services;

import java.util.List;

import com.edu.realestate.model.Favorite;

public interface FavoriteService {

	void addFavAds(String username, int advertisementId, int priority, String comments);
	
	Favorite getFavByAdId(String username, int advertisementId);

	boolean isFavAd(String username, int advertisementId);
	
	void removeFavAd(Integer id);
	
	List<Favorite> getFavByUser(String username);

}
