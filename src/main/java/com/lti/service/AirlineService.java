package com.lti.service;

import java.util.List;

import com.lti.dto.SearchFlightsDT;
import com.lti.entity.Flight;
import com.lti.entity.User;

public interface AirlineService {

	public int registerUser(User user);
	public List<Flight> searchFlights(SearchFlightsDT searchFlightsDT);
}
