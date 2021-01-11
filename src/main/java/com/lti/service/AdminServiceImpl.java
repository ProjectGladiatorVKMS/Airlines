package com.lti.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lti.entity.Flight;

@Repository
public class AdminServiceImpl implements AdminService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addFlight(Flight flight) {

		entityManager.merge(flight);
		
	}

	@Override
	public Flight displayFlight(int flightId) {
		Flight flight =  entityManager.find(Flight.class, flightId);
		return flight;
		//return null;
	}

	@Override
	public void deleteFight(int flightId) {

		//Flight flight = hibernateTemplate.load(Flight.class, flightId);
		//hibernateTemplate.delete(flight);
	}

	@Override
	public List<Flight> displayAllFlights() {
		//List<Flight> flight = hibernateTemplate.loadAll(Flight.class);
		//return flight;
		return null;
	}

}
