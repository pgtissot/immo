package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.RealEstate;

public class RealEstateDaoJDBC extends AbstractDaoJDBC implements RealEstateDao {

	private ApartmentDao adao;
	private CommercialPropertyDao cdao;
	private HouseDao hdao;
	private LandDao ldao;
	private OtherPropertyDao odao;
	private ParkingDao kdao;

	public RealEstateDaoJDBC() {
		adao = new ApartmentDaoJDBC();
		cdao = new CommercialPropertyDaoJDBC();
		hdao = new HouseDaoJDBC();
		ldao = new LandDaoJDBC();
		odao = new OtherPropertyDaoJDBC();
		kdao = new ParkingDaoJDBC();
	}


	@Override
	public void create(RealEstate element) {
		// TODO Auto-generated method stub

	}

	@Override
	public RealEstate read(Integer id) {
		RealEstate re = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM allproperties ap " +
			" JOIN real_estate r ON ap.id = r.id " +
			" WHERE ap.id = " + id;

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				switch (rs.getString("realtype")) {
				case "Apartment":
					re = adao.read(rs.getInt("id"));
					break;
				case "Commercial":
					re = cdao.read(rs.getInt("id"));
					break;
				case "House":
					re = hdao.read(rs.getInt("id"));
					break;
				case "Land":
					re = ldao.read(rs.getInt("id"));
					break;
				case "Other":
					re = odao.read(rs.getInt("id"));
					break;
				case "Parking":
					re = kdao.read(rs.getInt("id"));
					break;
				}

				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				re.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("RealEstateDaoJDBC error : " + e.getLocalizedMessage());
		}

		return re;
	}

	@Override
	public void update(RealEstate element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
