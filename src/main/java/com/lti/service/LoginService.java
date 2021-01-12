package com.lti.service;

public interface LoginService {

	public void validateUser(String emailId, String password);
	public void validateAdmin(String username, String password);
}
