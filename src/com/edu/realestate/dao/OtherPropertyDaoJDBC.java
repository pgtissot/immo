package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.model.City;
import com.edu.realestate.model.OtherProperty;

public class OtherPropertyDaoJDBC extends AbstractDaoJDBC implements OtherPropertyDao {

	@Override
	public void create(OtherProperty element) {
		// TODO Auto-generated method stub

	}

	@Override
	public OtherProperty read(Integer id) {
		OtherProperty op = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM other_property op " +
			" JOIN real_estate r ON r.id = op.id" +
			" WHERE op.id = " + id;

			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				op = new OtherProperty();
				op.setId(rs.getInt("id"));
				op.setPrice(rs.getInt("price"));
				op.setArea(rs.getShort("area"));
				op.setAvailable(rs.getString("available").equals("Y"));
				
				CityDao cdao = new CityDaoJDBC();
				City city = cdao.read(rs.getInt("city_id"));
				op.setCity(city);

			}
		} catch (SQLException e) {
			System.out.println("OtherPropertyDaoJDBC error : " + e.getLocalizedMessage());
		}

		return op;
	}

	@Override
	public void update(OtherProperty element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
