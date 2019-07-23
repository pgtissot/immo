package com.edu.realestate.services;

import com.edu.realestate.dao.UserDao;
import com.edu.realestate.dao.UserDaoJDBC;
import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.User;

public class UserServiceImpl implements UserService {

	UserDao udao;
	
	public UserServiceImpl() {
		udao = new UserDaoJDBC();
	}
	
	@Override
	public void register(Advertiser adv) {
		udao.create(adv);
	}

	@Override
	public void register(Moderator mod) {
		udao.create(mod);
	}

	@Override
	public User authenticate(String login, String password) throws AuthenticationException {
		return udao.authenticate(login, password);
	}

	@Override
	public void disconnect(User u) {
		// TODO Auto-generated method stub

	}

}
