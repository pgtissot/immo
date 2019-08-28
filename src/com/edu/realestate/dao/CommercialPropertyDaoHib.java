package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.CommercialProperty;

@Repository
public class CommercialPropertyDaoHib extends AbstractDaoHib implements CommercialPropertyDao {

	@Override
	public void create(CommercialProperty cp) {
		Session session = getSession();
		session.save(cp);
	}

	@Override
	public CommercialProperty read(Integer id) {
		Session session = getSession();
		CommercialProperty cp = null;
		cp = session.load(CommercialProperty.class, id);
		return cp;
	}

	@Override
	public void update(CommercialProperty cp) {
		Session session = getSession();
		session.save(cp);
	}

	@Override
	public void delete(CommercialProperty cp) {
		Session session = getSession();
		session.delete(cp);
	}

}
