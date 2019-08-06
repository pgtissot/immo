package com.edu.realestate.dao;

import com.edu.realestate.model.*;

public class TestUserDao {

	public static void main(String[] args) throws Exception {

		UserDao udao = new UserDaoJDBC();

		Moderator mod = new Moderator("rene.goscinny@polite.fr", "NON", null);
//		udao.create(mod);

		Advertiser adv = new Advertiser("achille.talon@polite.fr", "erudit", "M", "Achille", "Talon", "0123456789");
//		udao.create(adv);
		
//		udao.delete("rene.goscinny@polite.fr");
//		udao.delete("achille.talon@polite.fr");
		
		User u = udao.authenticate("pgthebest@blah.fr", "tractopelle");
		System.out.println(u);
		u = udao.authenticate(mod.getUsername(), mod.getPassword());
		System.out.println(u);

	}	
}