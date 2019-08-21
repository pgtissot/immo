package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.House;

public class HouseDaoHib extends AbstractDaoHib implements HouseDao {

	private static final Logger LOGGER = LogManager.getLogger(HouseDaoHib.class);

	@Override
	public void create(House h) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(h);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une House");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public House read(Integer id) {
		House house = null;

		try (Session session = getSessionFactory().openSession()) {
			house = session.load(House.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire la House " + id);
			e.printStackTrace();
		}

		return house;
	}

	@Override
	public void update(House h) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(h);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater la House " + h.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(House h) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(h);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer la House " + h.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
