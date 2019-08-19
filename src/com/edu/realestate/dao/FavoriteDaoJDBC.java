package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Favorite;
import com.edu.realestate.model.Utils;

public class FavoriteDaoJDBC extends AbstractDaoJDBC implements FavoriteDao {

	private AdvertisementDao adao;

	public FavoriteDaoJDBC() {
		adao = new AdvertisementDaoJDBC();
	}

	@Override
	public void create(Favorite favorite) {

		try {
			Statement st = getConnection().createStatement();

			String colComments = "";
			String valComments = "";
			if (favorite.getComments() != null) {
				colComments = ", `comments`";
				valComments = ", '" + Utils.toUTF8(favorite.getComments().replace("'", "''")) + "'";
			}

			String req = "INSERT INTO favoris " +
			"(`owner`, `advertisement_id`, `priority`" + colComments +") VALUES " +
			"('" + favorite.getUsername() + "', " +
			favorite.getAd().getId() + ", " +
			favorite.getPriority() +
			valComments + ")";

			st.executeUpdate(req);
		} catch (Exception e) {
			System.out.println("UserDaoJDBC create error : " + e.getLocalizedMessage());
		}

	}

	@Override
	public Favorite read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Favorite read(String username, int advertisementId) {

		Favorite fav = null;
		
		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM favoris WHERE owner = '" + username + "' AND advertisement_id = " + advertisementId;
			ResultSet rs = st.executeQuery(req);

			if (rs.next()) {
				Advertisement ad = adao.read(advertisementId);
				fav = new Favorite(rs.getInt("id"), username, ad, rs.getInt("priority"), rs.getString("comments"));
			}
		} catch (Exception e) {
			System.out.println("FavoriteDaoJDBC read error : " + e.getLocalizedMessage());
		}
		
		return fav;
	}

	@Override
	public void update(Favorite element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		try {
			Statement st = getConnection().createStatement();
			String req = "DELETE FROM favoris WHERE id = " + id;
			st.executeUpdate(req);
		} catch (Exception e) {
			System.out.println("FavoriteDaoJDBC delete error : " + e.getLocalizedMessage());
		}
	}

	@Override
	public boolean isFavAd(String username, int advertisementId) {
		return read(username, advertisementId) != null;
	}

}
