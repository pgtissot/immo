package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.Land;


public class LandDaoJDBC extends AbstractDaoJDBC implements LandDao {

	@Override
	public void create(Land l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Land read(Integer id) {
		Land land = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM land l " +
			" JOIN real_estate r ON r.id = l.id" +
			" WHERE l.id = " + id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				land = new Land();
				land.setId(rs.getInt("id"));
				land.setPrice(rs.getInt("price"));
				land.setArea(rs.getShort("area"));
				land.setAvailable(rs.getString("available").equals("Y"));
				
				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				land.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("LandDaoJDBC error : " + e.getLocalizedMessage());
		}

		return land;
	}

	@Override
	public void update(Land l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Land l) {
		// TODO Auto-generated method stub

	}

}
