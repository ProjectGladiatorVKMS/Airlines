package com.lti.dto;

public class LoginStatusDT {

	private boolean result;
	private int fetchedId;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getFetchedUserId() {
		return fetchedId;
	}
	public void setFetchedUserId(int fetchedId) {
		this.fetchedId = fetchedId;
	}
	
}
