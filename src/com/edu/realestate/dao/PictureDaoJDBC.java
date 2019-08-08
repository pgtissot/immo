package com.edu.realestate.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.edu.realestate.model.Picture;

public class PictureDaoJDBC extends AbstractDaoJDBC implements PictureDao {

	@Override
	public void create(Picture element) {
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

			if (rs.next()) {
				picture = new Picture();
				picture.setId(rs.getInt("id"));
				picture.setCodage(String.valueOf(rs.getInt("codage")));
				
				InputStream inputStream = rs.getBlob("content").getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				 
				while ((bytesRead = inputStream.read(buffer)) != -1) {
				    outputStream.write(buffer, 0, bytesRead);
				}
				 
				byte[] imageBytes = outputStream.toByteArray();
				 
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				 
				inputStream.close();
				outputStream.close();
				
				picture.setData(base64Image);
			}
		} catch (Exception e) {
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
			String req = "SELECT p.* FROM picture p JOIN advertisement a ON a.id = p.advertisement_id WHERE a.id = " + id;
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
