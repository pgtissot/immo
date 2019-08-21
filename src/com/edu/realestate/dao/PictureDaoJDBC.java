package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.edu.realestate.model.Picture;

public class PictureDaoJDBC extends AbstractDaoJDBC implements PictureDao {

	@Override
	public void create(Picture p) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("resource")
	@Override
	public Picture read(Integer id) {
		Picture picture = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM picture WHERE id = " + id;

			ResultSet rs = st.executeQuery(req);
			picture = new Picture();

			if (rs.next()) {
				picture.setId(rs.getInt("id"));
				picture.setData(rs.getBlob("content").getBytes(1, (int)rs.getBlob("content").length()));
			} else {
				picture.setData(new String("images/image-not-found.jpg").getBytes());
			}
		} catch (Exception e) {
			System.out.println("PictureDaoJDBC error : " + e.getLocalizedMessage());
		}

		return picture;
	}

	@Override
	public void update(Picture p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Picture p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Picture> getAllPicturesByAd(Integer id) {
		List<Picture> list = new ArrayList<>();
		Picture picture = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT p.* FROM picture p JOIN advertisement a ON a.id = p.advertisement_id WHERE a.id = " + id;
			ResultSet rs = st.executeQuery(req);
			picture = new Picture();
			
			while (rs.next()) {
				picture.setId(rs.getInt("id"));
				picture.setData(rs.getBlob("content").getBytes(1, (int)rs.getBlob("content").length()));
				list.add(picture);
			}
			
			if (list.size() == 0) {
				picture.setData(new String("images/image-not-found.jpg").getBytes());
				list.add(picture);
			}
				
		} catch (Exception e) {
			System.out.println("PictureDaoJDBC error : " + e.getLocalizedMessage());
		}

		return list;
	}
}
