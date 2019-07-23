package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.edu.realestate.mapping.AdvertisementMapper;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.RealEstate;

public class AdvertisementDaoJDBC extends AbstractDaoJDBC implements AdvertisementDao {

	private UserDao udao;
	private RealEstateDao rdao;
	private PictureDao pdao;
	
	public AdvertisementDaoJDBC () {
		udao = new UserDaoJDBC();
		rdao = new RealEstateDaoJDBC();
		pdao = new PictureDaoJDBC();
	}

	@Override
	public void create(Advertisement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Advertisement read(Integer id) {
		Advertisement advertisement = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM advertisement a " +
			" JOIN real_estate r ON a.real_estate_id = r.id " + 
			" JOIN allproperties ap ON r.id = ap.id " +
			" WHERE a.id = " + id + 
			" AND a.status = 'Validated'";
			
			ResultSet rs = st.executeQuery(req);
		
			if (rs.next()) {
				Advertiser advertiser = (Advertiser) udao.read(rs.getString("advertiser_id"));
				RealEstate re = rdao.read(rs.getInt("real_estate_id"));
				List<Picture> pictures = pdao.getAllAdvPictures(rs.getInt("id"));
				advertisement = AdvertisementMapper.resultToAdvertisement(rs, advertiser, re, pictures);
			}
		} catch (SQLException e) {
			System.out.println("AdvertisementDaoJDBC error : " + e.getLocalizedMessage());
		}

		return advertisement;

	}

	@Override
	public void update(Advertisement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
}
