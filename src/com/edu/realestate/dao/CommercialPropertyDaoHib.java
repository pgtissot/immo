package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.CommercialProperty;

public class CommercialPropertyDaoHib extends AbstractDaoHib implements CommercialPropertyDao {

	private static final Logger LOGGER = LogManager.getLogger(CommercialPropertyDaoHib.class);

	@Override
	public void create(CommercialProperty cp) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cp);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une Commercial Property");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public CommercialProperty read(Integer id) {
		CommercialProperty cp = null;

		try (Session session = getSessionFactory().openSession()) {
			cp = session.load(CommercialProperty.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire la Commercial Property " + id);
			e.printStackTrace();
		}

		return cp;
	}

	@Override
	public void update(CommercialProperty cp) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cp);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater la Commercial Property " + cp.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(CommercialProperty cp) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(cp);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer la Commercial Property " + cp.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

}
