package com.lti.dao;

import com.lti.entity.Admin;
import com.lti.entity.User;

public interface LoginDao {

	public User validateUser(String email, String password);
	public Admin validateAdmin(String username, String password);
}
