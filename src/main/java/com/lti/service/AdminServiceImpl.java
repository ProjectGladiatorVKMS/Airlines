package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lti.entity.Flight;

@Repository
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int addFlight(Flight flight) {

		int result = (int) hibernateTemplate.save(flight);
		return result;
	}

	@Override
	public Flight displayFlight(int flightId) {
		Flight flight = hibernateTemplate.get(Flight.class, flightId);
		return flight;
	}

	@Override
	public void deleteFight(int flightId) {

		Flight flight = hibernateTemplate.load(Flight.class, flightId);
		hibernateTemplate.delete(flight);
	}

	@Override
	public List<Flight> displayAllFlights() {
		List<Flight> flight = hibernateTemplate.loadAll(Flight.class);
		return flight;
	}

}
