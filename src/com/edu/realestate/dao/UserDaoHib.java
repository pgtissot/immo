package com.edu.realestate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.User;

public class UserDaoHib extends AbstractDaoHib implements UserDao {

	private static final Logger LOGGER = LogManager.getLogger(UserDaoHib.class);

	@Override
	public void create(User u) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible de créer un User");
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
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

		try (Session session = getSessionFactory().openSession()) {
			user = session.load(User.class, username);
		} catch (Exception e) {
			LOGGER.error("Impossible de lire le User " + username);
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void update(User u) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'updater le User " + u.getUsername());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User u) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(u);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le User " + u.getUsername());
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String username) {
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(read(username));
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Impossible d'effacer le User " + username);
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
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
