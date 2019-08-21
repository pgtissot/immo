package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.City;

public class CityDaoHib extends AbstractDaoHib implements CityDao {

	private static final Logger LOGGER = LogManager.getLogger(CityDaoHib.class);

	@Override
	public void create(City city) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(city);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer une City");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public City read(Integer id) {
		City city = null;

		try (Session session = getSessionFactory().openSession()) {
			city = session.load(City.class, id);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire la City " + id);
			e.printStackTrace();
		}

		return city;
	}

	@Override
	public void update(City city) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(city);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater la City " + city.getId());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(City city) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(city);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer la City " + city.getId());
			if (transaction != null) transaction.rollback();
		}
	}

	@Override
	public List<City> listMatching(String comparator, boolean exact) {
		List<City> list = new ArrayList<>();
		
		String where = "WHERE ";
		if (comparator.matches("[0-9]+")) {
			where += "postcode LIKE '" + comparator + "%'";
		} else {
			if (!exact)
				where += "name LIKE '%" + comparator + "%'";
			else
				where += "name = '" + comparator + "'";
		}

		try (Session session = getSessionFactory().openSession()) {
			list = session.createQuery("FROM City " + where, City.class).list();
		} catch (Exception e) {
			LOGGER.error("Impossible de matcher des City");
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public List<City> listAll() {
		List<City> list = new ArrayList<>();
		
		try (Session session = getSessionFactory().openSession()) {
			list = session.createQuery("FROM City", City.class).list();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire les City");
			e.printStackTrace();
		}

		return list;
	}


}
