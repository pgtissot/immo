package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.Apartment;
import com.edu.realestate.model.City;

public class ApartmentDaoJDBC extends AbstractDaoJDBC implements ApartmentDao {

	@Override
	public void create(Apartment element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Apartment read(Integer id) {
		Apartment apartment = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM apartment a " +
			" JOIN real_estate r ON r.id = a.id " +
			" WHERE a.id = " + id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				apartment = new Apartment();
				apartment.setId(rs.getInt("id"));
				apartment.setPrice(rs.getInt("price"));
				apartment.setArea(rs.getShort("area"));
				apartment.setAvailable(rs.getString("available").equals("Y"));
				apartment.setRooms(rs.getInt("rooms"));
				apartment.setFloor(rs.getString("floor"));
				if (rs.getString("energy_level") != null)
					apartment.setEnergyLevel(rs.getString("energy_level").charAt(0));
				else
					apartment.setEnergyLevel('N');
				if (rs.getString("gas_level") != null)
					apartment.setGasLevel(rs.getString("gas_level").charAt(0));
				else
					apartment.setGasLevel('N');
				apartment.setElevator(rs.getString("elevator").equals("Y"));
				apartment.setIntercom(rs.getString("intercom").equals("Y"));
				apartment.setBalcony(rs.getString("balcony").equals("Y"));
				apartment.setTerrace(rs.getString("terrace").equals("Y"));
				apartment.setGarage(rs.getString("garage").equals("Y"));
				apartment.setParking(rs.getString("parking").equals("Y"));
				apartment.setAlarm(rs.getString("alarm").equals("Y"));
				apartment.setDigicode(rs.getString("digicode").equals("Y"));

				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				apartment.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("ApartmentDaoJDBC error : " + e.getLocalizedMessage());
		}

		return apartment;
	}

	@Override
	public void update(Apartment element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
