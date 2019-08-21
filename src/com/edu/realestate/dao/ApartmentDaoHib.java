package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Apartment;

public class ApartmentDaoHib extends AbstractDaoHib implements ApartmentDao {

	private static final Logger LOGGER = LogManager.getLogger(ApartmentDaoHib.class);

	@Override
	public void create(Apartment ap) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(ap);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer un Apartment");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Apartment read(Integer id) {
		Apartment apartment = null;

		try (Session session = getSessionFactory().openSession()) {
			apartment = session.load(Apartment.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire l'Apartment " + id);
			e.printStackTrace();
		}

		return apartment;
	}

	@Override
	public void update(Apartment ap) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(ap);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater l'Apartment " + ap.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Apartment ap) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(ap);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer l'Apartment " + ap.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
