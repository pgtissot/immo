package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.RealEstate;

@Repository
public class RealEstateDaoHib extends AbstractDaoHib implements RealEstateDao {

	@Override
	public void create(RealEstate re) {
		Session session = getSession();
		session.save(re);
	}

	@Override
	public RealEstate read(Integer id) {
		Session session = getSession();
		RealEstate re = null;
		re = session.load(RealEstate.class, id);
		return re;
	}

	@Override
	public void update(RealEstate re) {
		Session session = getSession();
		session.save(re);
	}

	@Override
	public void delete(RealEstate re) {
		Session session = getSession();
		session.delete(re);
	}

	public long countRealEstates() {
		Session session = getSession();
		long count = 0;
		count = session.createQuery("SELECT count(*) FROM RealEstate WHERE available = 'Y'", Long.class).getSingleResult();
		return count;
	}

}
