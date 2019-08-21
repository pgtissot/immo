package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.OtherProperty;

public class OtherPropertyDaoHib extends AbstractDaoHib implements OtherPropertyDao {

	private static final Logger LOGGER = LogManager.getLogger(OtherPropertyDaoHib.class);

	@Override
	public void create(OtherProperty op) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(op);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une Other Property");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public OtherProperty read(Integer id) {
		OtherProperty op = null;

		try (Session session = getSessionFactory().openSession()) {
			op = session.load(OtherProperty.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire l'Other Property " + id);
			e.printStackTrace();
		}

		return op;
	}

	@Override
	public void update(OtherProperty op) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(op);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater l'Other Property " + op.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(OtherProperty op) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(op);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer l'Other Property " + op.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
