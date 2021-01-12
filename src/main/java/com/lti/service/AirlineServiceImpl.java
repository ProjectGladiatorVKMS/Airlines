package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lti.dao.GenericDao;
import com.lti.entity.User;

@Service
public class AirlineServiceImpl implements AirlineService {

	

	@Autowired
	GenericDao dao;

	@Override
	public int registerUser(User user) {
		User updatedUser = (User) dao.save(user);
		return updatedUser.getUserId();
	}

}
