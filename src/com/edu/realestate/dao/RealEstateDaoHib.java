package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.TransactionType;

public class RealEstateDaoHib extends AbstractDaoHib implements RealEstateDao {

	private static final Logger LOGGER = LogManager.getLogger(RealEstateDaoHib.class);

	@Override
	public void create(RealEstate re) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(re);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer un RealEstate");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public RealEstate read(Integer id) {
		RealEstate re = null;

		try (Session session = getSessionFactory().openSession()) {
			re = session.load(RealEstate.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le RealEstate " + id);
			e.printStackTrace();
		}

		return re;
	}

	@Override
	public void update(RealEstate re) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(re);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater le RealEstate " + re.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(RealEstate re) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(re);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le RealEstate " + re.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	public long countRealEstates() {
		long count = 0;
		
		try (Session session = getSessionFactory().openSession()) {
			count = session.createQuery("SELECT count(*) FROM RealEstate WHERE available = 'Y'", Long.class).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le nombre de RealEstate");
			e.printStackTrace();
		}

		return count;
	}

}
