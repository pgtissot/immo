
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
import com.edu.realestate.model.SearchCriteria;

public class SearchDaoJDBC extends AbstractDaoJDBC implements SearchDao {

	private UserDao udao;
	private RealEstateDao rdao;
	private PictureDao pdao;
	
	public SearchDaoJDBC () {
		udao = new UserDaoJDBC();
		rdao = new RealEstateDaoJDBC();
		pdao = new PictureDaoJDBC();
	}
	
	@Override
	public List<Advertisement> search(SearchCriteria sc) {
		List<Advertisement> lads = new ArrayList<>();

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM advertisement a " +
			" JOIN real_estate r ON a.real_estate_id = r.id " +
			" JOIN allproperties ap ON r.id = ap.id " +
			" JOIN city c ON r.city_id = c.id " +
			" WHERE c.id = " + sc.getCityId() +
			" AND a.status = 'Validated'";
			
			if (sc.getQuery() != null) {
				req += " AND a.transaction_type = '" + sc.getQuery() + "'";
			}
			if (sc.getType() != null) {
				req += " AND ap.realtype = '" + sc.getType() + "'";
			}
			if (sc.getAreaMin() > 0) {
				req += " AND r.area >= " + sc.getAreaMin();
			}
			if (sc.getAreaMax() > 0) {
				req += " AND r.area <= " + sc.getAreaMax();
			}
			if (sc.getPriceMin() > 0) {
				req += " AND r.price >= " + sc.getPriceMin();
			}
			if (sc.getPriceMax() > 0) {
				req += " AND r.price <= " + sc.getPriceMax();
			}
			
			ResultSet rs = st.executeQuery(req);
					
			while (rs.next()) {
				Advertiser advertiser = (Advertiser) udao.read(rs.getString("advertiser_id"));
				RealEstate re = rdao.read(rs.getInt("real_estate_id"));
				List<Picture> pictures = pdao.getAllAdvPictures(rs.getInt("id"));
				Advertisement advertisement = AdvertisementMapper.resultToAdvertisement(rs, advertiser, re, pictures);

				lads.add(advertisement);

			}

		} catch (SQLException e) {
			System.out.println("SearchDaoJDBC read error : " + e.getLocalizedMessage());
		}

		return lads;

	}

		
}
