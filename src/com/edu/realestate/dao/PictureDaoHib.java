package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Picture;

public class PictureDaoHib extends AbstractDaoHib implements PictureDao {

	private static final Logger LOGGER = LogManager.getLogger(PictureDaoHib.class);

	@Override
	public void create(Picture p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une Picture");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Picture read(Integer id) {
		Picture picture = null;

		try (Session session = getSessionFactory().openSession()) {
			picture = session.load(Picture.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire la Picture " + id);
			e.printStackTrace();
		}

		if (picture == null) {
			picture = new Picture();
			picture.setData(new String("images/image-not-found.jpg").getBytes());
		}
		return picture;

	}

	@Override
	public void update(Picture p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater la Picture " + p.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Picture p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer la Picture " + p.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<Picture> getAllPicturesByAd(Integer id) {
		List<Picture> list = new ArrayList<>();

		try (Session session = getSessionFactory().openSession()) {
			list = session.createQuery("FROM Advertisement WHERE id = " + id, Advertisement.class).getSingleResult().getPictures();
		} catch (Exception e) {
			LOGGER.error("Impossible de récupérer les Picture de l'Ad " + id);
			e.printStackTrace();
		}

		if (list.size() == 0) {
			Picture picture = new Picture();
			picture.setData(new String("images/image-not-found.jpg").getBytes());
			list.add(picture);
		}

		return list;
	}
}
