package com.edu.realestate.dao;

import com.edu.realestate.model.Favorite;

public interface FavoriteDao extends AbstractDao<Favorite> {

	Favorite read(String username, int advertisementId);

	boolean isFavAd(String username, int advertisementId);

}
