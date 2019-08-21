package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Land;


public class LandDaoHib extends AbstractDaoHib implements LandDao {

	private static final Logger LOGGER = LogManager.getLogger(LandDaoHib.class);

	@Override
	public void create(Land l) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(l);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une Land");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Land read(Integer id) {
		Land land = null;

		try (Session session = getSessionFactory().openSession()) {
			land = session.load(Land.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le Land " + id);
			e.printStackTrace();
		}

		return land;
	}

	@Override
	public void update(Land l) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(l);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater le Land " + l.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Land l) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(l);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le Land " + l.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
