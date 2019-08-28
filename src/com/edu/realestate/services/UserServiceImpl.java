package com.edu.realestate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.realestate.dao.UserDao;
import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.User;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao udao;
	
	@Override
	@Transactional(readOnly=false)
	public void register(Advertiser adv) {
		udao.create(adv);
	}

	@Override
	@Transactional(readOnly=false)
	public void register(Moderator mod) {
		udao.create(mod);
	}

	@Override
	public User authenticate(String login, String password) throws AuthenticationException {
		User u = udao.authenticate(login, password);
		// Never stock a password in a object
		u.setPassword("");
		return u;
	}

	@Override
	public void disconnect(User u) {
		// TODO Auto-generated method stub

	}
}
