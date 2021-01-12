package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.GenericDao;
import com.lti.entity.Flight;
import com.lti.function.MiscFunction;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	GenericDao dao;

	@Autowired
	MiscFunction miscFunction;

	@Override
	public void addFlight(Flight flight) {
		flight.setDuration(miscFunction.calcDuration(flight.getDeparture(), flight.getArrival()));
		dao.save(flight);
	}

	@Override
	public Flight displayFlight(int flightId) {
		Flight flight = dao.fetchById(Flight.class, flightId);
		return flight;
	}

	@Override
	public void deleteFight(int flightId) {
		Flight flight = dao.fetchById(Flight.class, flightId);
		dao.delete(flight);
	}

	@Override
	public List<Flight> displayAllFlights() {
		List<Flight> flightList = dao.fetchAll(Flight.class);
		return flightList;
	}

}
