package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.dto.SearchFlightsDT;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;

@Repository
public class AirlineOperationDaoImpl implements AirlineOperationDao {

	@PersistenceContext
	protected EntityManager entityManager; 
	
	@Autowired
	protected GenericDao dao;
	
	@Override
	public List<Flight> searchFlightOperation(SearchFlightsDT searchFlightsDT) {

		if(searchFlightsDT.getTravelClass().equalsIgnoreCase("economy")) {
			String fetchedQuery = "select f from Flight f where f.source=:qsource and f.destination=:qdestination and f.journeyDate=:qdate and f.economySeats >= :qtravellers";
			Query query = entityManager.createQuery(fetchedQuery);
			query.setParameter("qsource", searchFlightsDT.getSource());
			query.setParameter("qdestination", searchFlightsDT.getDestination());
			query.setParameter("qdate", searchFlightsDT.getTravelDate());
			query.setParameter("qtravellers", searchFlightsDT.getNoOfPassengers());
			return query.getResultList();
			
		}
		else {
			String fetchedQuery = "select f from Flight f where f.source=:qsource and f.destination=:qdestination and f.journeyDate=:qdate and f.businessSeats >= :qtravellers";
			Query query = entityManager.createQuery(fetchedQuery);
			query.setParameter("qsource", searchFlightsDT.getSource());
			query.setParameter("qdestination", searchFlightsDT.getDestination());
			query.setParameter("qdate", searchFlightsDT.getTravelDate());
			query.setParameter("qtravellers", searchFlightsDT.getNoOfPassengers());
			return query.getResultList();
		}
		
	}

	@Override
	public void addPassenger(Passenger passenger) {
		Passenger updatedPassenger = (Passenger) dao.save(passenger);
	}

	@Override
	public List<Passenger> fetchPassenger(int bookingId) {

		String fetchedQuery = "select p from Passenger p where p.booking.bookingId=:qbookingId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("qbookingId", bookingId);
		List<Passenger> passengers = query.getResultList();
		return passengers;
	}

	@Override
	public Flight fetchFlight(Booking booking) {

		Flight bookedFlight = booking.getFlight();
		int bookedFlightId = bookedFlight.getFlightId();
		String fetchedQuery = "select f from Flight f where f.flightId=:qflightId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("qflightId", bookedFlightId);
		Flight fetchedFlight = (Flight) query.getSingleResult();
		return fetchedFlight;
	}

}
