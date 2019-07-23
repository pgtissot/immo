package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.House;

public class HouseDaoJDBC extends AbstractDaoJDBC implements HouseDao {

	@Override
	public void create(House element) {
		// TODO Auto-generated method stub

	}

	@Override
	public House read(Integer id) {
		House house = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM house h " +
			" JOIN real_estate r ON r.id = h.id " +
			" WHERE h.id = " + id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				house = new House();
				house.setId(rs.getInt("id"));
				house.setPrice(rs.getInt("price"));
				house.setArea(rs.getShort("area"));
				house.setAvailable(rs.getString("available").equals("Y"));
				house.setRooms(rs.getInt("rooms"));
				house.setLandArea(rs.getInt("land_area"));
				if (rs.getString("energy_level") != null)
					house.setEnergyLevel(rs.getString("energy_level").charAt(0));
				if (rs.getString("gas_level") != null)
					house.setGasLevel(rs.getString("gas_level").charAt(0));
				house.setCellar(rs.getString("cellar").equals("Y"));
				house.setAlarm(rs.getString("alarm").equals("Y"));
				house.setSwimmingPool(rs.getString("swimming_pool").equals("Y"));
				
				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				house.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("HouseDaoJDBC error : " + e.getLocalizedMessage());
		}

		return house;

	}

	@Override
	public void update(House element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
