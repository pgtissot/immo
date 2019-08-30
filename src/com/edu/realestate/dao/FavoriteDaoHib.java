package com.edu.realestate.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Favorite;

@Repository
public class FavoriteDaoHib extends AbstractDaoHib implements FavoriteDao {

	@Override
	public void create(Favorite fav) {
		Session session = getSession();
		session.save(fav);
	}

	@Override
	public Favorite read(Integer id) {
		Session session = getSession();
		Favorite fav = null;
		fav = session.load(Favorite.class, id);
		return fav;
	}

	@Override
	public Favorite read(String username, int advertisementId) {
		Session session = getSession();
		List<Favorite> favs = session.createQuery("FROM Favorite WHERE username = '" + username + "' AND advertisement.id = " + advertisementId, Favorite.class).getResultList();
		return (favs.size() == 0 ? null : favs.get(0));
	}

	@Override
	public void update(Favorite fav) {
		Session session = getSession();
		session.save(fav);
	}

	@Override
	public void delete(Favorite fav) {
		Session session = getSession();
		session.delete(fav);
	}

	@Override
	public boolean isFavAd(String username, int advertisementId) {
		return read(username, advertisementId) != null;
	}

	@Override
	public List<Favorite> getFavByUser(String username) {
		Session session = getSession();
		List<Favorite> favs = session.createQuery("FROM Favorite WHERE owner = '" + username + "'", Favorite.class).getResultList();
		return favs;
	}

}
