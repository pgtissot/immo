package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.Parking;


public class ParkingDaoJDBC extends AbstractDaoJDBC implements ParkingDao {

	@Override
	public void create(Parking p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parking read(Integer id) {
		Parking park = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM parking p " +
			" JOIN real_estate r ON r.id = p.id " +
			" WHERE p.id = " +  id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				park = new Parking();
				park.setId(rs.getInt("id"));
				park.setPrice(rs.getInt("price"));
				park.setArea(rs.getShort("area"));
				park.setAvailable(rs.getString("available").equals("Y"));
				
				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				park.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("ParkingDaoJDBC error : " + e.getLocalizedMessage());
		}

		return park;
	}

	@Override
	public void update(Parking p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Parking p) {
		// TODO Auto-generated method stub

	}

}
