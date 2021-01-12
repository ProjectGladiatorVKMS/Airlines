package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.LoginDao;
import com.lti.entity.Admin;
import com.lti.entity.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao dao;
	
	
	@Override
	public void validateUser(String emailId, String password) {
		User user = dao.validateUser(emailId, password);
		System.out.println("login service "+user);
	}


	@Override
	public void validateAdmin(String username, String password) {
		Admin admin = dao.validateAdmin(username, password);
		System.out.println("login service "+admin);
	}

}
