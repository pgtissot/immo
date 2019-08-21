package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Parking;

public class ParkingDaoHib extends AbstractDaoHib implements ParkingDao {

	private static final Logger LOGGER = LogManager.getLogger(ParkingDaoHib.class);

	@Override
	public void create(Parking p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer un Parking");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Parking read(Integer id) {
		Parking p = null;

		try (Session session = getSessionFactory().openSession()) {
			p = session.load(Parking.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le Parking " + id);
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public void update(Parking p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater le Parking " + p.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Parking p) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(p);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le Parking " + p.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
