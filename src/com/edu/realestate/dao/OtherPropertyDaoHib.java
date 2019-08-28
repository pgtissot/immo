package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.OtherProperty;

@Repository
public class OtherPropertyDaoHib extends AbstractDaoHib implements OtherPropertyDao {

	@Override
	public void create(OtherProperty op) {
		Session session = getSession();
		session.save(op);
	}

	@Override
	public OtherProperty read(Integer id) {
		Session session = getSession();
		OtherProperty op = null;
		op = session.load(OtherProperty.class, id);
		return op;
	}

	@Override
	public void update(OtherProperty op) {
		Session session = getSession();
		session.save(op);
	}

	@Override
	public void delete(OtherProperty op) {
		Session session = getSession();
		session.delete(op);
	}

}
