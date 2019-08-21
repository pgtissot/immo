package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.RealEstateType;
import com.edu.realestate.model.SearchCriteria;

public class SearchDaoHib extends AbstractDaoHib implements SearchDao {

	private static final Logger LOGGER = LogManager.getLogger(SearchDaoHib.class);

	@Override
	public List<Advertisement> search(SearchCriteria sc) {
		List<Advertisement> lads = new ArrayList<>();

		try (Session session = getSessionFactory().openSession()) {

			String otherReq = "";
			String exclude = "";
			String sort = " releaseDate DESC";
			boolean apt = sc.getRealEstateType().equals(RealEstateType.valueOf("Apartment"));
			boolean house = sc.getRealEstateType().equals(RealEstateType.valueOf("House"));

			if (sc.getCityId() != 0) {
				otherReq += " AND realEstate.city.id IN ";
				if (sc.getDistance() != 0)
					otherReq += " (SELECT id FROM City c2 " + " WHERE greatcircle(c2.latitude, c2.longitude,"
							+ sc.getLatitude() + "," + sc.getLongitude() + ") <= " + sc.getDistance()
							+ " OR greatcircle(c2.latitude, c2.longitude," + sc.getLatitude() + "," + sc.getLongitude()
							+ ") IS NULL)";
				else
					otherReq += "(SELECT id from City WHERE id = " + sc.getCityId() + ")";
			}
			
			if (sc.getQuery() != null)
				otherReq += " AND ( description LIKE '%" + sc.getQuery() + "%'" + " OR title LIKE '%"
						+ sc.getQuery() + "%' )";

			if (sc.getTransactionType() != null)
				otherReq += " AND transactionType = '" + sc.getTransactionType() + "'";

			if (sc.getAreaMin() < 0)
				sc.setAreaMin(0);
			if (sc.getAreaMax() > 10000)
				sc.setAreaMax(10000);
			if (sc.getAreaMin() > sc.getAreaMax() && sc.getAreaMax() > 0) {
				int tmp = sc.getAreaMin();
				sc.setAreaMin(sc.getAreaMax());
				sc.setAreaMax(tmp);
			}
			
			if (sc.getAreaMin() > 0)
				otherReq += " AND realEstate.area >= " + sc.getAreaMin();
			if (sc.getAreaMax() > 0)
				otherReq += " AND realEstate.area <= " + sc.getAreaMax();

			if (sc.getPriceMin() < 0)
				sc.setPriceMin(0);
			if (sc.getPriceMax() > 100000000)
				sc.setPriceMax(100000000);
			if (sc.getPriceMin() > sc.getPriceMax() && sc.getPriceMax() > 0) {
				int tmp = sc.getPriceMin();
				sc.setPriceMin(sc.getPriceMax());
				sc.setPriceMax(tmp);
			}
			
			if (sc.getPriceMin() > 0)
				otherReq += " AND realEstate.price >= " + sc.getPriceMin();
			if (sc.getPriceMax() > 0)
				otherReq += " AND realEstate.price <= " + sc.getPriceMax();

			if (house) {
				if (sc.getLandMin() < 0)
					sc.setLandMin(0);
				if (sc.getLandMax() > 1000000)
					sc.setLandMax(1000000);
				if (sc.getLandMin() > sc.getLandMax() && sc.getLandMax() > 0) {
					int tmp = sc.getLandMin();
					sc.setLandMin(sc.getLandMax());
					sc.setLandMax(tmp);
				}

				if (sc.getLandMin() > 0 || sc.getLandMax() > 0) {
					if (sc.getLandMin() > 0)
						otherReq += " AND realEstate.landArea >= " + sc.getLandMin();
					if (sc.getLandMax() > 0)
						otherReq += " AND realEstate.landArea <= " + sc.getLandMax();
				}
			}

			if (apt || house) {
				if (sc.getRoomsMin() < 0)
					sc.setRoomsMin(0);
				if (sc.getRoomsMax() > 100)
					sc.setRoomsMax(100);
				if (sc.getRoomsMin() > sc.getRoomsMax() && sc.getRoomsMax() > 0) {
					int tmp = sc.getRoomsMin();
					sc.setRoomsMin(sc.getRoomsMax());
					sc.setRoomsMax(tmp);
				}

				if (sc.getRoomsMin() > 0 || sc.getRoomsMax() > 0) {
					if (apt) {
						if (sc.getRoomsMin() > 0)
							otherReq += " AND realEstate.rooms >= " + sc.getRoomsMin();
						if (sc.getRoomsMax() > 0)
							otherReq += " AND realEstate.rooms <= " + sc.getRoomsMax();
					}
					if (house) {
						if (sc.getRoomsMin() > 0)
							otherReq += " AND realEstate.rooms >= " + sc.getRoomsMin();
						if (sc.getRoomsMax() > 0)
							otherReq += " AND realEstate.rooms <= " + sc.getRoomsMax();
					}
				}
			}

			/* OPTIONS */
			if (apt || house) {
				if (apt) {
					if (sc.hasElevator())
						otherReq += " AND realEstate.elevator = 'Y'";
					if (sc.hasIntercom())
						otherReq += " AND realEstate.intercom = 'Y'";
					if (sc.hasBalcony())
						otherReq += " AND realEstate.balcony = 'Y'";
					if (sc.hasTerrace())
						otherReq += " AND realEstate.terrace = 'Y'";
					if (sc.hasGarage())
						otherReq += " AND realEstate.garage = 'Y'";
					if (sc.hasParking())
						otherReq += " AND realEstate.parking = 'Y'";
					if (sc.hasAlarm())
						otherReq += " AND realEstate.alarm = 'Y'";
					if (sc.hasDigicode())
						otherReq += " AND realEstate.digicode = 'Y'";
				}
				if (house) {
					if (sc.hasCellar())
						otherReq += " AND realEstate.cellar = 'Y'";
					if (sc.hasAlarm())
						otherReq += " AND realEstate.alarm = 'Y'";
					if (sc.hasSwimmingPool())
						otherReq += " AND realEstate.swimming_pool = 'Y'";
				}
			}

			if (apt || house) {
				if (apt) {
					if ((int) sc.getEnergyLevel() != 0)
						otherReq += " AND realEstate.energyLevel REGEXP '[A-" + sc.getEnergyLevel() + "]'";
					if ((int) sc.getGasLevel() != 0)
						otherReq += " AND realEstate.gasLevel REGEXP '[A-" + sc.getGasLevel() + "]'";
				}
				if (house) {
					if ((int) sc.getEnergyLevel() != 0)
						otherReq += " AND realEstate.energyLevel REGEXP '[A-" + sc.getEnergyLevel() + "]'";;
					if ((int) sc.getGasLevel() != 0)
						otherReq += " AND realEstate.gasLevel REGEXP '[A-" + sc.getGasLevel() + "]'";;
				}
			}

			/* SORT AND PAGINATION */
			if (sc.getSort() != null)
				sort = sc.getSort();

			/* IN DETAILS, EXCLUDE THE AD DETAILED IN THE SEARCH */
			if (sc.getExclude() != 0) {
				exclude = " AND id != " + sc.getExclude();
			}
			
 			String req = "FROM Advertisement WHERE realEstate.available = 'Y' AND status = 'Validated' " + exclude + otherReq + " ORDER BY " + sort;

			if (sc.getLimit() > 0)
				lads = session.createQuery(req, Advertisement.class).setMaxResults(sc.getLimit()).list();
			else
				lads = session.createQuery(req, Advertisement.class).list();
				
		
		} catch (Exception e) {
			LOGGER.error("Impossible de récupérer les Ads à partir du SearchCriteria");
			e.printStackTrace();
		}

		return lads;

	}

}
