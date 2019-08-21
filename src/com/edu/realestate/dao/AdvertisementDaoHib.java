package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.edu.realestate.mapping.AdvertisementMapper;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.TransactionType;

public class AdvertisementDaoHib extends AbstractDaoHib implements AdvertisementDao {

	private static final Logger LOGGER = LogManager.getLogger(AdvertisementDaoHib.class);

	@Override
	public void create(Advertisement adv) {
		// TODO Auto-generated method stub

	}

	@Override
	public Advertisement read(Integer id) {
		Advertisement advertisement = null;

		try (Session session = getSessionFactory().openSession()) {
			advertisement = session.createQuery("from Advertisement WHERE id = " + id + " AND realEstate.available = 'Y' AND status = 'Validated'",
												Advertisement.class).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire l'Advertisement " + id);
			e.printStackTrace();
		}

		return advertisement;
	}

	@Override
	public void update(Advertisement adv) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Advertisement adv) {
		// TODO Auto-generated method stub

	}
	
	public long countSaleAds() {
		long count = 0;

		try (Session session = getSessionFactory().openSession()) {
			count = session.createQuery("SELECT count(*) FROM Advertisement " +
										" WHERE transactionType = '" + TransactionType.Sale + "' AND status = 'Validated'", Long.class).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le nombre d'Ads de vente");
			e.printStackTrace();
		}

		return count;
		
	}

	public long countRentAds() {
		long count = 0;
		
		try (Session session = getSessionFactory().openSession()) {
			count = session.createQuery("SELECT count(*) FROM Advertisement " +
										" WHERE transactionType = '" + TransactionType.Rent + "' AND status = 'Validated'", Long.class).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le nombre d'Ads de location");
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<Advertisement> getLatest(int number) {
		List<Advertisement> lads = new ArrayList<>();
		
		try (Session session = getSessionFactory().openSession()) {
			lads = session.createQuery("FROM Advertisement " +
										"WHERE status = 'Validated'", Advertisement.class).setMaxResults(number).list();
		} catch (Exception e) {
			LOGGER.error("Impossible de lire les " + number + " dernières Ads");
			e.printStackTrace();
		}

		return lads;
	}

}
