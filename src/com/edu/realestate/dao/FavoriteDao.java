package com.edu.realestate.dao;

import com.edu.realestate.model.Favorite;

public interface FavoriteDao extends AbstractDao<Favorite> {

	void create(Favorite favorite);
	
	Favorite read(Integer id);

	Favorite read(String username, int advertisementId);

	boolean isFavAd(String username, int advertisementId);

	void delete(Integer id);
	

}
