package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edu.realestate.model.Picture;

public class PictureDaoJDBC extends AbstractDaoJDBC implements PictureDao {

	@Override
	public void create(Picture element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Picture read(Integer id) {
		Picture picture = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM picture WHERE id = " + id;

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				picture = new Picture();
				picture.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			System.out.println("PictureDaoJDBC error : " + e.getLocalizedMessage());
		}

		return picture;
	}

	@Override
	public void update(Picture element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Picture> getAllPicturesByAd(Integer id) {
		List<Picture> list = new ArrayList<>();
		Picture picture = null;

		try {
			Statement st = getConnection().createStatement();
			String req = String.format("SELECT * FROM advertisement a JOIN picture p ON a.id = p.advertisement_id WHERE a.id = %d", id);
			ResultSet rs = st.executeQuery(req);

			while (rs.next()) {
				picture = new Picture();
				picture.setId(rs.getInt("id"));
				list.add(picture);
			}
		} catch (SQLException e) {
			System.out.println("PictureDaoJDBC error : " + e.getLocalizedMessage());
		}

		return list;
	}

}
