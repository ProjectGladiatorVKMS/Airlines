package com.lti.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.dto.SearchFlightsDT;
import com.lti.dto.TicketDT;
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

		if (searchFlightsDT.getTravelClass().equalsIgnoreCase("economy")) {
			String fetchedQuery = "select f from Flight f where f.source=:qsource and f.destination=:qdestination and f.journeyDate=:qdate and f.economySeats >= :qtravellers";
			Query query = entityManager.createQuery(fetchedQuery);
			query.setParameter("qsource", searchFlightsDT.getSource());
			query.setParameter("qdestination", searchFlightsDT.getDestination());
			query.setParameter("qdate", searchFlightsDT.getTravelDate());
			query.setParameter("qtravellers", searchFlightsDT.getNoOfPassengers());
			return query.getResultList();

		} else {
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

	@Override
	public TicketDT fetchTicket(int bookingId) {

		// fetching Flight
		Booking booking = new Booking();
		booking = dao.fetchById(Booking.class, bookingId);
		Flight bookedFlight = booking.getFlight();
		int bookedFlightId = bookedFlight.getFlightId();
		String fetchedQuery1 = "select f from Flight f where f.flightId=:qflightId";
		Query query1 = entityManager.createQuery(fetchedQuery1);
		query1.setParameter("qflightId", bookedFlightId);
		Flight fetchedFlight = (Flight) query1.getSingleResult();

		// fetching passenger
		String fetchedQuery2 = "select p from Passenger p where p.booking.bookingId=:qbookingId";
		Query query2 = entityManager.createQuery(fetchedQuery2);
		query2.setParameter("qbookingId", bookingId);
		List<Passenger> passengerList = query2.getResultList();

		// generating Ticket
		TicketDT ticketDT = new TicketDT();
		ticketDT.setBookingId(bookingId);
		ticketDT.setFlightId(fetchedFlight.getFlightId());
		ticketDT.setSource(fetchedFlight.getSource());
		ticketDT.setDestination(fetchedFlight.getDestination());
		ticketDT.setJourneyDate(booking.getJourneyDate().toString());
		ticketDT.setArrivalTime(fetchedFlight.getArrival());
		ticketDT.setDepartureTime(fetchedFlight.getDeparture());
		ticketDT.setTravelClass(booking.getTravelClass());

		List<String> passengerNames = new ArrayList<String>();
		for (Passenger p : passengerList) {
			passengerNames.add(p.getfName() + " " + p.getlName());
		}
		ticketDT.setPassengerName(passengerNames);
		return ticketDT;
	}

}
