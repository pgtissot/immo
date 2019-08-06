package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.mapping.RealEstateMapper;
import com.edu.realestate.model.RealEstate;

public class RealEstateDaoJDBC extends AbstractDaoJDBC implements RealEstateDao {

	@Override
	public void create(RealEstate element) {
		// TODO Auto-generated method stub

	}

	@Override
	public RealEstate read(Integer id) {
		RealEstate re = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM allproperties ap " + " JOIN real_estate r ON ap.id = r.id " + " WHERE ap.id = "
					+ id;
			ResultSet rs = st.executeQuery(req);
			if (rs.next())
				re = RealEstateMapper.resultToRealEstate(rs);
		} catch (Exception e) {
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

	public int countRealEstates() {
		int count = 0;
		
		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT count(*) AS number FROM real_estate r WHERE r.available = 'Y'";

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				count = rs.getInt("number");
			}
		} catch (SQLException e) {
			System.out.println("RealEstateDaoJDBC error : " + e.getLocalizedMessage());
		}

		return count;
	}

}
