package com.lti.dao;

import java.util.List;

import com.lti.dto.SearchFlightsDT;
import com.lti.entity.Flight;

public interface AirlineOperationDao {

	public List<Flight> searchFlightOperation(SearchFlightsDT searchFlightsDT);
}
