package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.City;

@Repository
public class CityDaoHib extends AbstractDaoHib implements CityDao {

	@Override
	public void create(City city) {
		Session session = getSession();
		session.save(city);
	}

	@Override
	public City read(Integer id) {
		Session session = getSession();
		City city = null;
		city = session.get(City.class, id);
		return city;
	}

	@Override
	public void update(City city) {
		Session session = getSession();
		session.save(city);
	}

	@Override
	public void delete(City city) {
		Session session = getSession();
		session.delete(city);
	}

	@Override
	public List<City> listMatching(String comparator, boolean exact) {
		Session session = getSession();
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

		list = session.createQuery("FROM City " + where, City.class).list();
		return list;

	}

	@Override
	public List<City> listAll() {
		Session session = getSession();
		List<City> list = new ArrayList<>();
		list = session.createQuery("FROM City", City.class).list();
		return list;
	}


}
