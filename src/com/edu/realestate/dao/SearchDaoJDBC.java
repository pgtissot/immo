
package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edu.realestate.mapping.AdvertisementMapper;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.RealEstateType;
import com.edu.realestate.model.SearchCriteria;

public class SearchDaoJDBC extends AbstractDaoJDBC implements SearchDao {

	private UserDao udao;
	private RealEstateDao rdao;
	private PictureDao pdao;

	public SearchDaoJDBC() {
		udao = new UserDaoJDBC();
		rdao = new RealEstateDaoJDBC();
		pdao = new PictureDaoJDBC();
	}

	@Override
	public List<Advertisement> search(SearchCriteria sc) {
		List<Advertisement> lads = new ArrayList<>();

		try {
			Statement st = getConnection().createStatement();
			String otherJoin = "";
			String otherReq = "";
			String apJoin = "";
			String apReq = "";
			String exclude = "";
			String sort = "";
			String pagination = "";
			boolean apt = sc.getRealEstateType().equals(RealEstateType.valueOf("Apartment"));
			boolean house = sc.getRealEstateType().equals(RealEstateType.valueOf("House"));

			if (sc.getRealEstateType() != null) {
				apJoin = " JOIN allproperties ap ON r.id = ap.id";
				apReq = " AND ap.realtype = '" + sc.getRealEstateType() + "'";
			}

			if (sc.getCityId() != 0) {
				otherReq += " AND c.id IN ";
				if (sc.getDistance() != 0)
					otherReq += " (SELECT id FROM city c2 " + " WHERE greatcircle(c2.latitude, c2.longitude,"
							+ sc.getLatitude() + "," + sc.getLongitude() + ") <= " + sc.getDistance()
							+ " OR greatcircle(c2.latitude, c2.longitude," + sc.getLatitude() + "," + sc.getLongitude()
							+ ") IS NULL)";
				else
					otherReq += "(SELECT id from city where id = " + sc.getCityId() + ")";
			}

			if (sc.getQuery() != null)
				otherReq += " AND ( d.description LIKE '%" + sc.getQuery() + "%'" + " OR d.title LIKE '%"
						+ sc.getQuery() + "%' )";

			if (sc.getTransactionType() != null)
				otherReq += " AND d.transaction_type = '" + sc.getTransactionType() + "'";

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
				otherReq += " AND r.area >= " + sc.getAreaMin();
			if (sc.getAreaMax() > 0)
				otherReq += " AND r.area <= " + sc.getAreaMax();

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
				otherReq += " AND r.price >= " + sc.getPriceMin();
			if (sc.getPriceMax() > 0)
				otherReq += " AND r.price <= " + sc.getPriceMax();

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
					otherJoin += " JOIN house h ON r.id = h.id ";
					if (sc.getLandMin() > 0)
						otherReq += " AND h.price >= " + sc.getPriceMin();
					if (sc.getLandMax() > 0)
						otherReq += " AND h.price <= " + sc.getPriceMax();
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
						otherJoin += " JOIN apartment a ON r.id = a.id ";
						if (sc.getRoomsMin() > 0)
							otherReq += " AND a.rooms >= " + sc.getRoomsMin();
						if (sc.getRoomsMax() > 0)
							otherReq += " AND a.rooms <= " + sc.getRoomsMax();
					}
					if (house) {
						otherJoin += " JOIN house h ON r.id = h.id ";
						if (sc.getRoomsMin() > 0)
							otherReq += " AND h.rooms >= " + sc.getRoomsMin();
						if (sc.getRoomsMax() > 0)
							otherReq += " AND h.rooms <= " + sc.getRoomsMax();
					}
				}
			}

			/* OPTIONS */
			if (apt || house) {
				if (apt) {
					if (sc.hasElevator())
						otherReq += " AND a.elevator = 'Y'";
					if (sc.hasIntercom())
						otherReq += " AND a.intercom = 'Y'";
					if (sc.hasBalcony())
						otherReq += " AND a.balcony = 'Y'";
					if (sc.hasTerrace())
						otherReq += " AND a.terrace = 'Y'";
					if (sc.hasGarage())
						otherReq += " AND a.garage = 'Y'";
					if (sc.hasParking())
						otherReq += " AND a.parking = 'Y'";
					if (sc.hasAlarm())
						otherReq += " AND a.alarm = 'Y'";
					if (sc.hasDigicode())
						otherReq += " AND a.digicode = 'Y'";
				}
				if (house) {
					if (sc.hasCellar())
						otherReq += " AND h.cellar = 'Y'";
					if (sc.hasAlarm())
						otherReq += " AND h.alarm = 'Y'";
					if (sc.hasSwimmingPool())
						otherReq += " AND h.swimming_pool = 'Y'";
				}
			}

			if (apt || house) {
				if (apt) {
					if ((int) sc.getEnergyLevel() != 0)
						otherReq += " AND a.energy_level REGEXP '[A-" + sc.getEnergyLevel() + "]'";
					if ((int) sc.getGasLevel() != 0)
						otherReq += " AND a.gas_level REGEXP '[A-" + sc.getGasLevel() + "]'";
				}
				if (house) {
					if ((int) sc.getEnergyLevel() != 0)
						otherReq += " AND h.energy_level REGEXP '[A-" + sc.getEnergyLevel() + "]'";;
					if ((int) sc.getGasLevel() != 0)
						otherReq += " AND h.gas_level REGEXP '[A-" + sc.getGasLevel() + "]'";;
				}
			}

			/* SORT AND PAGINATION */
			if (sc.getSort() != null)
				sort = " ORDER BY " + sc.getSort();

			if (sc.getLimit() > 0)
				pagination = " LIMIT " + sc.getLimit() + " OFFSET " + sc.getOffset();

			/* IN DETAILS, EXCLUDE THE AD DETAILED IN THE SEARCH */
			if (sc.getExclude() != 0) {
				exclude = " AND d.id != " + sc.getExclude();
			}
			
			String req = "SELECT * FROM advertisement d " + " JOIN real_estate r ON d.real_estate_id = r.id " + apJoin
					+ " JOIN city c ON r.city_id = c.id " + otherJoin + " WHERE r.available = 'Y'"
					+ " AND d.status = 'Validated' " + exclude + apReq + otherReq + sort + pagination;

			ResultSet rs = st.executeQuery(req);

			while (rs.next()) {
				Advertiser advertiser = (Advertiser) udao.read(rs.getString("advertiser_id"));
				RealEstate re = rdao.read(rs.getInt("real_estate_id"));
				List<Picture> pictures = pdao.getAllPicturesByAd(rs.getInt("id"));
				Advertisement advertisement = AdvertisementMapper.resultToAdvertisement(rs, advertiser, re, pictures);

				lads.add(advertisement);

			}

		} catch (SQLException e) {
			System.out.println("SearchDaoJDBC read error : " + e.getLocalizedMessage());
		}

		return lads;

	}

}
