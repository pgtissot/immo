package com.edu.realestate.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.User;

@Repository
public class UserDaoHib extends AbstractDaoHib implements UserDao {

	@Override
	public void create(User u) {
		Session session = getSession();
		session.save(u);
	}

	@Override
	public User read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User read(String username) {
		Session session = getSession();
		User user = null;
		user = session.get(User.class, username);
		return user;
	}

	@Override
	public void update(User u) {
		Session session = getSession();
		session.saveOrUpdate(u);
	}

	@Override
	public void delete(User u) {
		Session session = getSession();
		session.delete(u);
	}

	@Override
	public void delete(String username) {
		Session session = getSession();
		session.delete(read(username));
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
