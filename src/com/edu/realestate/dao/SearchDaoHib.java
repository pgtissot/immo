package com.edu.realestate.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.RealEstateType;
import com.edu.realestate.model.SearchCriteria;

@Repository
public class SearchDaoHib extends AbstractDaoHib implements SearchDao {

	@Autowired
	CityDao cdao;

	@Override
	public List<Advertisement> search(SearchCriteria sc) {
		Session session = getSession();
		List<Advertisement> lads = new ArrayList<>();

		String otherReq = "";
		String exclude = "";
		String sort = " releaseDate DESC";
		boolean apt = sc.getRealEstateType().equals(RealEstateType.valueOf("Apartment"));
		boolean house = sc.getRealEstateType().equals(RealEstateType.valueOf("House"));

		if (sc.getRealEstateType() != null) {
			otherReq += " AND realEstate.id IN (SELECT id from " + sc.getRealEstateType() + ")";
		}

		if (sc.getCityId() != 0) {
			otherReq += " AND realEstate.city.id IN ";

			if (sc.getDistance() != 0) {

				sc.setDistance(Math.min(50, sc.getDistance()));

				City c = cdao.read(sc.getCityId());

				ReturningWork<List<Integer>> rw = new ReturningWork<List<Integer>>() {

					@Override
					public List<Integer> execute(Connection connection) throws SQLException {
						try (CallableStatement function = connection.prepareCall(
								"{ SELECT id FROM City c WHERE greatcircle(c.latitude, c.longitude, ?, ?) < ?"
										+ " OR greatcircle(c.latitude, c.longitude, ?, ?) IS NULL }")) {
							function.registerOutParameter(1, Types.ARRAY);
							function.setDouble(1, c.getLatitude());
							function.setDouble(2, c.getLongitude());
							function.setDouble(3, sc.getDistance());
							function.execute();
							List<Integer> list = new ArrayList<>();
							ResultSet rs = function.getResultSet();
							while (rs.next())
								list.add(rs.getInt("id"));
							return list;
						}
					}
				};

				List<Integer> cityInDistance = session.doReturningWork(rw);
				String cityList = cityInDistance.toString();
				otherReq += "(" + cityList.substring(1, cityList.length() - 1) + ")";

			} else
				otherReq += "(" + sc.getCityId() + ")";
		}

		if (sc.getQuery() != null)
			otherReq += " AND ( description LIKE '%" + sc.getQuery() + "%'" + " OR title LIKE '%" + sc.getQuery()
					+ "%' )";

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
					otherReq += " AND realEstate.energyLevel REGEXP '[A-" + sc.getEnergyLevel() + "]'";
				;
				if ((int) sc.getGasLevel() != 0)
					otherReq += " AND realEstate.gasLevel REGEXP '[A-" + sc.getGasLevel() + "]'";
				;
			}
		}

		/* SORT AND PAGINATION */
		if (sc.getSort() != null)
			sort = sc.getSort();

		/* IN DETAILS, EXCLUDE THE AD DETAILED IN THE SEARCH */
		if (sc.getExclude() != 0) {
			exclude = " AND id != " + sc.getExclude();
		}

		String req = "FROM Advertisement WHERE realEstate.available = 'Y' AND status = 'Validated' " + exclude
				+ otherReq + " ORDER BY " + sort;

		if (sc.getLimit() > 0)
			lads = session.createQuery(req, Advertisement.class).setMaxResults(sc.getLimit()).list();
		else
			lads = session.createQuery(req, Advertisement.class).list();

		return lads;

	}

}
