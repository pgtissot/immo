package com.edu.realestate.services;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.User;

public interface UserService {

	void register(Advertiser adv);
	
	void register(Moderator mod);
	
	User authenticate(String login, String password) throws AuthenticationException;

	void disconnect(User u);
		
	void update(User u);
	
	User read(String username);
	
}
