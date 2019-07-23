package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.CommercialProperty;

public class CommercialPropertyDaoJDBC extends AbstractDaoJDBC implements CommercialPropertyDao {

	@Override
	public void create(CommercialProperty element) {
		// TODO Auto-generated method stub

	}

	@Override
	public CommercialProperty read(Integer id) {
		CommercialProperty cp = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM commercial_property cp " +
			" JOIN real_estate r ON r.id = cp.id " +
			" WHERE cp.id = " + id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				cp = new CommercialProperty();
				cp.setId(rs.getInt("id"));
				cp.setPrice(rs.getInt("price"));
				cp.setArea(rs.getShort("area"));
				cp.setAvailable(rs.getString("available").equals("Y"));
				
				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				cp.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("CommercialPropertyDaoJDBC error : " + e.getLocalizedMessage());
		}

		return cp;
	}

	@Override
	public void update(CommercialProperty element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
