package com.edu.realestate.dao;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.User;

public interface UserDao extends AbstractDao<User> {

	void create(User user);
	
	User read(String username);

	User authenticate(String username, String password) throws AuthenticationException;
	
	void delete(String username);

}
