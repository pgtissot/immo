package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.House;

@Repository
public class HouseDaoHib extends AbstractDaoHib implements HouseDao {

	@Override
	public void create(House h) {
		Session session = getSession();
		session.save(h);
	}

	@Override
	public House read(Integer id) {
		Session session = getSession();
		House house = null;
		house = session.load(House.class, id);
		return house;
	}

	@Override
	public void update(House h) {
		Session session = getSession();
		session.save(h);
	}

	@Override
	public void delete(House h) {
		Session session = getSession();
		session.delete(h);
	}

}
