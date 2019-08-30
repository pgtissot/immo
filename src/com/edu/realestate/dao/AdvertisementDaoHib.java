package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.TransactionType;

@Repository
public class AdvertisementDaoHib extends AbstractDaoHib implements AdvertisementDao {

	@Override
	public void create(Advertisement adv) {
		Session session = getSession();
		session.save(adv);
	}

	@Override
	public Advertisement read(Integer id) {
		Session session = getSession();
		Advertisement advertisement = null;
		advertisement = session.createQuery("from Advertisement WHERE id = " + id + " AND realEstate.available = 'Y' AND status = 'Validated'",
												Advertisement.class).getSingleResult();
		return advertisement;
	}

	@Override
	public Advertisement readAllStatus(Integer id) {
		Session session = getSession();
		Advertisement advertisement = null;
		advertisement = session.createQuery("from Advertisement WHERE id = " + id + " AND realEstate.available = 'Y'", Advertisement.class).getSingleResult();
		return advertisement;
	}

	@Override
	public void update(Advertisement adv) {
		Session session = getSession();
		session.save(adv);
	}

	@Override
	public void delete(Advertisement adv) {
		Session session = getSession();
		session.delete(adv);
	}
	
	@Override
	public Advertisement findAdvertisementByNumber(String name) {
		Session session = getSession();
		Advertisement advertisement = null;
		advertisement = session.createQuery("from Advertisement WHERE adNumber = '" + name + "' AND realEstate.available = 'Y' AND status = 'Validated'",
												Advertisement.class).getSingleResult();
		return advertisement;
	}

	@Override
	public List<Advertisement> findAdvertisementByCity(int cityId) {
		Session session = getSession();
		List<Advertisement> lads = null;
		lads = session.createQuery("FROM Advertisement WHERE realEstate.city.id = " + cityId + "' AND realEstate.available = 'Y' AND status = 'Validated'",
												Advertisement.class).getResultList();
		return lads;
	}
	
	@Override
	public long countSaleAds() {
		Session session = getSession();
		long count = 0;
		count = session.createQuery("SELECT count(*) FROM Advertisement " +
									" WHERE transactionType = '" + TransactionType.Sale + "' AND status = 'Validated'", Long.class).getSingleResult();
		return count;
		
	}

	@Override
	public long countRentAds() {
		Session session = getSession();
		long count = 0;
		count = session.createQuery("SELECT count(*) FROM Advertisement " +
									" WHERE transactionType = '" + TransactionType.Rent + "' AND status = 'Validated'", Long.class).getSingleResult();
		return count;
	}

	@Override
	public List<Advertisement> getLatest(int number) {
		Session session = getSession();
		List<Advertisement> lads = new ArrayList<>();
		lads = session.createQuery("FROM Advertisement WHERE status = 'Validated' ORDER BY releaseDate DESC", Advertisement.class).setMaxResults(number).list();
		return lads;
	}

	@Override
	public void validateAdvertisement(int adId) throws RealEstateException {
		Advertisement ad = readAllStatus(adId);
		ad.setStatus(AdStatus.Validated);
		update(ad);
	}

	@Override
	public void refuseAdvertisement(int adId, String refusedComment) throws RealEstateException {
		Advertisement ad = readAllStatus(adId);
		ad.setStatus(AdStatus.Refused);
		ad.setRefusedComment(refusedComment);
		update(ad);
	}

	@Override
	public List<Advertisement> findAdvertisementsByStatus(AdStatus status) throws RealEstateException {
		Session session = getSession();
		List<Advertisement> lads = new ArrayList<>();
		lads = session.createQuery("FROM Advertisement WHERE status = '" + status.name() + "'", Advertisement.class).list();
		return lads;
	}

	@Override
	public List<String> listMatching(String comparator, boolean exact) {
		Session session = getSession();
		List<String> list = new ArrayList<>();
		
		String where = "WHERE ";
		if (!exact)
			where += "adNumber LIKE '%" + comparator + "%'";
		else
			where += "adNumber = '" + comparator + "'";

		list = session.createQuery("SELECT adNumber FROM Advertisement " + where, String.class).list();
		return list;

	}

}
