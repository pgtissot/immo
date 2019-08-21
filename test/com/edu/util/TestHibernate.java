package com.edu.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Apartment;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Favorite;
import com.edu.realestate.model.House;
import com.edu.realestate.model.Land;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.User;

public class TestHibernate {

	public static void main(String[] args) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) { //Auto-Closable, try to close the resource if it doesn't work

			transaction = session.beginTransaction();

//			City c = session.load(City.class, 1370);
//			System.out.println(c);
//
//			User u = session.load(User.class, "pgthebest@blah.fr");
//			System.out.println(u);

//			Moderator mod = new Moderator("rene.goscinny@polite.fr", "NON", null);
//			session.save(mod);
//			System.out.println(session.load(Moderator.class, "rene.goscinny@polite.fr"));
//			session.delete(mod);

//			Apartment a = session.load(Apartment.class, 48);
//			System.out.println(a);
//
//			House h = session.load(House.class, 52);
//			System.out.println(h);
//
//			Advertisement ad = session.load(Advertisement.class, 2);
//			System.out.println(ad);
//
//			Favorite f = session.load(Favorite.class, 6);
//			System.out.println(f);
//
//			List<City> cities = session.createQuery("from City", City.class).list();
//			cities.forEach(s -> System.out.println(s));

//			transaction.commit();
			
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			e.printStackTrace();
		}
	}
}
