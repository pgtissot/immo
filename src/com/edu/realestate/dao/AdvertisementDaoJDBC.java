package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public AdvertisementDaoJDBC() {
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
			String req = "SELECT a.id as ad_id, a.*, r.* FROM advertisement a " +
			" JOIN real_estate r ON a.real_estate_id = r.id " +
			" JOIN allproperties ap ON r.id = ap.id " +
			" WHERE a.id = " + id +
			" AND r.available = 'Y'" +
			" AND a.status = 'Validated'";

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				Advertiser advertiser = (Advertiser) udao.read(rs.getString("advertiser_id"));
				RealEstate re = rdao.read(rs.getInt("real_estate_id"));
				List<Picture> pictures = pdao.getAllPicturesByAd(rs.getInt("ad_id"));
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
	
	public int countSaleAds() {
		int count = 0;
		
		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT count(*) AS number FROM advertisement WHERE transaction_type = 'Sale' AND status = 'Validated'";

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				count = rs.getInt("number");
			}
		} catch (SQLException e) {
			System.out.println("AdvertisementDaoJDBC error : " + e.getLocalizedMessage());
		}

		return count;
		
	}

	public int countRentAds() {
		int count = 0;
		
		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT count(*) AS number FROM advertisement WHERE transaction_type = 'Rent' AND status = 'Validated'";

			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				count = rs.getInt("number");
			}
		} catch (SQLException e) {
			System.out.println("AdvertisementDaoJDBC error : " + e.getLocalizedMessage());
		}

		return count;
		
	}

	@Override
	public List<Advertisement> getLatest(int number) {
		List<Advertisement> lads = new ArrayList<>();

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT a.id as ad_id, a.*, r.* FROM advertisement a " +
			" JOIN real_estate r ON a.real_estate_id = r.id " +
			" JOIN allproperties ap ON r.id = ap.id " +
			" ORDER BY a.release_date DESC LIMIT " + number;

			ResultSet rs = st.executeQuery(req);

			while (rs.next()) {
				Advertiser advertiser = (Advertiser) udao.read(rs.getString("advertiser_id"));
				RealEstate re = rdao.read(rs.getInt("real_estate_id"));
				List<Picture> pictures = pdao.getAllPicturesByAd(rs.getInt("ad_id"));
				lads.add(AdvertisementMapper.resultToAdvertisement(rs, advertiser, re, pictures));
			}
		} catch (SQLException e) {
			System.out.println("AdvertisementDaoJDBC error : " + e.getLocalizedMessage());
		}

		return lads;
	}

}
