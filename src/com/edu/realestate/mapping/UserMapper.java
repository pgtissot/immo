package com.edu.realestate.mapping;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.User;


public class UserMapper {

	public static User resultToUser(ResultSet rs) throws Exception {
		User user = null;
		
		// NO PASSWORD BACK IN THE OBJECT !
		switch (rs.getString("user_type")) {
		case "M" :
			user = new Moderator(rs.getString("username"), "", rs.getString("name"));
			break;
		default :
			user = new Advertiser(rs.getString("username"), "", rs.getString("title"),
					rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"));
			break;
		}
		
		return user;

	}
		
	public static String usertoResult(User user) throws Exception {

		String className = user.getClass().getSimpleName();
		String req = "INSERT INTO user";
		
		List<String> fields = new ArrayList<>();
		List<String> values = new ArrayList<>();
		
		try {
			fields.add("`username`");
			fields.add("`password`");
			values.add("'" + user.getUsername() + "'");
			values.add("'" + user.getPassword() + "'");

			switch (className) {
			case "Advertiser":
				Advertiser adv = (Advertiser) user;
				fields.add("`user_type`");
				fields.add("`title`");
				fields.add("`first_name`");
				fields.add("`last_name`");
				fields.add("`phone`");
				values.add("'A'");
				values.add("'" + adv.getTitle() + "'");
				values.add("'" + adv.getFirstName() + "'");
				values.add("'" + adv.getLastName() + "'");
				values.add("'" + adv.getPhone() + "'");
				break;
			case "Moderator":
				Moderator mod = (Moderator) user;
				fields.add("`user_type`");
				fields.add("`name`");
				values.add("'M'");
				values.add("'" + mod.getName() + "'");
				break;
			}

		} catch (Exception e) {
			System.out.println("UserMapper error" + e.getLocalizedMessage());
		}

		req += " (" + String.join(",", fields) + ") VALUES (" + String.join(",", values) + ")";
		return req;
	}
	
}
