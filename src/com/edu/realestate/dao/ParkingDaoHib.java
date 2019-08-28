package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Parking;

@Repository
public class ParkingDaoHib extends AbstractDaoHib implements ParkingDao {

	@Override
	public void create(Parking p) {
		Session session = getSession();
		session.save(p);
	}

	@Override
	public Parking read(Integer id) {
		Session session = getSession();
		Parking p = null;
		p = session.load(Parking.class, id);
		return p;
	}

	@Override
	public void update(Parking p) {
		Session session = getSession();
		session.save(p);
	}

	@Override
	public void delete(Parking p) {
		Session session = getSession();
		session.delete(p);
	}

}
