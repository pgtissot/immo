package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Apartment;

@Repository
public class ApartmentDaoHib extends AbstractDaoHib implements ApartmentDao {

	@Override
	public void create(Apartment ap) {
		Session session = getSession();
		session.save(ap);
	}

	@Override
	public Apartment read(Integer id) {
		Session session = getSession();
		Apartment apartment = null;
		apartment = session.load(Apartment.class, id);
		return apartment;
	}

	@Override
	public void update(Apartment ap) {
		Session session = getSession();
		session.save(ap);
	}

	@Override
	public void delete(Apartment ap) {
		Session session = getSession();
		session.delete(ap);
	}

}
