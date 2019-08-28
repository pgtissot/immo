package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Land;

@Repository
public class LandDaoHib extends AbstractDaoHib implements LandDao {

	@Override
	public void create(Land l) {
		Session session = getSession();
		session.save(l);
	}

	@Override
	public Land read(Integer id) {
		Session session = getSession();
		Land land = null;
		land = session.load(Land.class, id);
		return land;
	}

	@Override
	public void update(Land l) {
		Session session = getSession();
		session.save(l);
	}

	@Override
	public void delete(Land l) {
		Session session = getSession();
		session.delete(l);
	}

}
