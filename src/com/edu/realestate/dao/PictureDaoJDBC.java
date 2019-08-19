package com.edu.realestate.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
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
			picture = new Picture();

			if (rs.next()) {
				picture.setId(rs.getInt("id"));
				picture.setCodage(String.valueOf(rs.getInt("codage")));
				picture.setData(toBase64(rs.getBlob("content")));
			} else {
				picture.setData("images/image-not-found.jpg");
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
			picture = new Picture();
			
			while (rs.next()) {
				picture.setId(rs.getInt("id"));
				picture.setCodage(String.valueOf(rs.getInt("codage")));
				picture.setData(toBase64(rs.getBlob("content")));
				list.add(picture);
			}
			
			if (list.size() == 0) {
				picture.setData("images/image-not-found.jpg");
				list.add(picture);
			}
				
		} catch (Exception e) {
			System.out.println("PictureDaoJDBC error : " + e.getLocalizedMessage());
		}

		return list;
	}

	public String toBase64(Blob b) throws Exception {
		
		InputStream inputStream = b.getBinaryStream();
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
		
		return "data:image/jpeg;base64," + base64Image;
	}
}
