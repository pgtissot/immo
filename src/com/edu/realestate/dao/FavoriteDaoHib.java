package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Favorite;

public class FavoriteDaoHib extends AbstractDaoHib implements FavoriteDao {

	private static final Logger LOGGER = LogManager.getLogger(FavoriteDaoHib.class);

	@Override
	public void create(Favorite fav) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(fav);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer un Favorite");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Favorite read(Integer id) {
		Favorite fav = null;

		try (Session session = getSessionFactory().openSession()) {
			fav = session.load(Favorite.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le Favorite " + id);
			e.printStackTrace();
		}

		return fav;
	}

	@Override
	public Favorite read(String username, int advertisementId) {

		Favorite fav = null;
		
		try (Session session = getSessionFactory().openSession()) {
			fav = session.createQuery("FROM Favorite WHERE username = '" + username + "' AND ad.id = " + advertisementId, Favorite.class).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le Favorite du couple " + username + "-" + advertisementId);
			e.printStackTrace();
		}
		
		return fav;
	}

	@Override
	public void update(Favorite fav) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(fav);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater le Favorite " + fav.getId());
			if (transaction != null) transaction.rollback();
		}
	}

	@Override
	public void delete(Favorite fav) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(fav);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le Favorite " + fav.getId());
			if (transaction != null) transaction.rollback();
		}
	}

	@Override
	public boolean isFavAd(String username, int advertisementId) {
		return read(username, advertisementId) != null;
	}

}
