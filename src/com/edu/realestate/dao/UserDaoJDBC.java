package com.edu.realestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.mapping.UserMapper;
import com.edu.realestate.model.User;

public class UserDaoJDBC extends AbstractDaoJDBC implements UserDao {

	@Override
	public void create(User u) {
		String req = null;
		
		try {
			Statement st = getConnection().createStatement();
			req = UserMapper.usertoResult(u);
			st.executeUpdate(req);

		} catch (Exception e) {
			System.out.println("UserDaoJDBC create error : " + e.getLocalizedMessage());
		}

	}

	@Override
	public User read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User read(String username) {
		User user = null;

		try {
			Statement st = getConnection().createStatement();
			String req = "SELECT * FROM user_data WHERE username = '" + username + "'";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) user = UserMapper.resultToUser(rs);
		} catch (Exception e) {
			System.out.println("UserDaoJDBC read error : " + e.getLocalizedMessage());
		}

		return user;
	}

	@Override
	public void update(User u) {
		try {
			Statement st = getConnection().createStatement();
			String req = "UPDATE user_data SET password='" + u.getPassword() + "'" +
					" WHERE username='" + u.getUsername()+ "'";
			st.executeUpdate(req);
		} catch (SQLException e) {
			System.out.println("UserDaoJDBC delete error : " + e.getLocalizedMessage());
		}
	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String username) {
		try {
			Statement st = getConnection().createStatement();
			String req = "DELETE FROM user_data WHERE username = '" + username + "'";
			st.executeUpdate(req);
		} catch (SQLException e) {
			System.out.println("UserDaoJDBC delete error : " + e.getLocalizedMessage());
		}
	}

	@Override
	public User authenticate(String username, String password) throws AuthenticationException {
		User user = null;

		user = read(username);

		if (user == null || !user.getPassword().equals(password))
			throw new AuthenticationException("Erreur d'authentification.");

		return user;
	}

}
