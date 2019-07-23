package com.edu.jdbc;

import java.util.List;

import com.edu.realestate.dao.*;
import com.edu.realestate.model.*;

public class TestDao {

	public static void main(String[] args) throws Exception {

		CityDao cdao = new CityDaoJDBC();
		City city = cdao.read(12);
//		System.out.println(city);
		
		List<City> list = cdao.listAll();
//		for (City c : list)
//			System.out.println(c);

		UserDao udao = new UserDaoJDBC();
		User user = udao.read("pgt");
//		System.out.println(user);

		HouseDao hdao = new HouseDaoJDBC();
		House house = hdao.read(52);
//		System.out.println(house);

		ApartmentDao adao = new ApartmentDaoJDBC();
		Apartment apartment = adao.read(48);
//		System.out.println(apartment);
		
		Moderator mod = new Moderator("rene.goscinny@polite.fr", "NON", null);
//		udao.create(mod);

		Advertiser adv = new Advertiser("achille.talon@polite.fr", "erudit", "M", "Achille", "Talon", "0123456789");
//		udao.create(adv);
		
		AdvertisementDao ddao = new AdvertisementDaoJDBC();
		Advertisement advertisement = ddao.read(2);
//		System.out.println(advertisement);

//		udao.delete("rene.goscinny@polite.fr");
//		udao.delete("achille.talon@polite.fr");
		
/*		SearchCriteria sc = new SearchCriteria();
		sc.setQuery("Rent");
		sc.setType("Apartment");
		sc.setCityId(2003);
		sc.setAreaMin(25);
		sc.setPriceMax(700);
		SearchDao sdao = new SearchDaoJDBC();
		List<Advertisement> lads = sdao.search(sc);
		for (Advertisement a : lads)
			System.out.println(a);
*/

		User u = udao.authenticate("pgthebest@blah.fr", "tractopelle");
		System.out.println(u);
		u = udao.authenticate(mod.getUsername(), mod.getPassword());
		System.out.println(u);

	}	
}
