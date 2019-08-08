package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edu.realestate.model.City;

public class CityDaoJDBC extends AbstractDaoJDBC implements CityDao {

	@Override
	public void create(City element) {
		// TODO Auto-generated method stub

	}

	@Override
	public City read(Integer id) {
		City city = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM city WHERE id = " + id;

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				city = new City();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPostcode(rs.getString("postcode"));
				city.setLongitude(rs.getDouble("longitude"));
				city.setLatitude(rs.getDouble("latitude"));
			}
		} catch (SQLException e) {
			System.out.println("CityDaoJDBC error : " + e.getLocalizedMessage());
		}

		return city;
	}

	@Override
	public void update(City element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<City> listMatching(String comparator, boolean exact) {
		List<City> list = new ArrayList<>();
		
		try {
			Statement st = getConnection().createStatement();
			String where = "WHERE ";
			if (comparator.matches("[0-9]+")) {
				where += "postcode LIKE '" + comparator + "%'";
			} else {
				if (!exact)
					where += "name LIKE '%" + comparator + "%'";
				else
					where += "name = '" + comparator + "'";
			}

			ResultSet rs = st.executeQuery("SELECT * FROM city "+ where + "ORDER BY name");

			while (rs.next()) {
				City city = new City();

				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPostcode(rs.getString("postcode"));
				city.setLongitude(rs.getDouble("longitude"));
				city.setLatitude(rs.getDouble("latitude"));

				list.add(city);
			}
		} catch (SQLException e) {
			System.out.println("CityDaoJDBC error : " + e.getLocalizedMessage());
		}

		
		return list;

	}

	@Override
	public List<City> listAll() {
		List<City> list = new ArrayList<>();

		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM city ORDER BY name");

			while (rs.next()) {
				City city = new City();

				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPostcode(rs.getString("postcode"));
				city.setLongitude(rs.getDouble("longitude"));
				city.setLatitude(rs.getDouble("latitude"));

				list.add(city);
			}
		} catch (SQLException e) {
			System.out.println("CityDaoJDBC error : " + e.getLocalizedMessage());
		}

		return list;
	}

}
