package com.lti.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.AirlineOperationDao;
import com.lti.dao.GenericDao;
import com.lti.dto.BookingDT;
import com.lti.dto.SearchFlightsDT;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Payment;
import com.lti.entity.User;

@Service
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	AirlineOperationDao airlineDao;

	@Autowired
	GenericDao dao;

	@Override
	public int registerUser(User user) {
		User updatedUser = (User) dao.save(user);
		return updatedUser.getUserId();
	}

	@Override
	public List<Flight> searchFlights(SearchFlightsDT searchFlightsDT) {
		return airlineDao.searchFlightOperation(searchFlightsDT);
	}

	@Override
	@Transactional
	public int addBooking(BookingDT bookingDT) {

		User user = new User();
		Flight flight = new Flight();
		Booking booking = new Booking();
		Passenger passenger = new Passenger();

		booking.setJourneyDate(bookingDT.getJourneyDate());
		booking.setNoOfPassengers(bookingDT.getNoOfPassenger());
		booking.setCost(bookingDT.getCost());
		booking.setTicketMailingId(bookingDT.getEmailId());
		booking.setBookingDate(LocalDate.now());
		booking.setTravelClass(bookingDT.getTravelClass());

		user = dao.fetchById(User.class, bookingDT.getUserId());
		flight = dao.fetchById(Flight.class, bookingDT.getFlightId());
		
		System.out.println(flight);
		System.out.println(user);

		booking.setUser(user);
		booking.setFlight(flight);

		booking.setSource(flight.getSource());
		booking.setDestination(flight.getDestination());
		booking.setDeparture(flight.getDeparture());
		booking.setArrival(flight.getArrival());

		System.out.println(booking);
		Booking fetchedBooking = (Booking) dao.save(booking);
		int fetchedBookingId = fetchedBooking.getBookingId();
		System.out.println(fetchedBooking);

		HashSet<Passenger> passengers = new HashSet<Passenger>();
		for (Passenger p : bookingDT.getPassengerList()) {
			p.setBooking(fetchedBooking);
			p.setBookingStatus("Not Booked");
			airlineDao.addPassenger(p);
		}

		return fetchedBookingId;
	}

	@Override
	public void updateBooking(int bookinId) {
		List<Passenger> passengerList = airlineDao.fetchPassenger(bookinId);
		for (Passenger p : passengerList) {
			p.setBookingStatus("Confirmed");
			dao.save(p);
		}
		Booking booking = dao.fetchById(Booking.class, bookinId);
		int confirmedSeats = booking.getNoOfPassengers();

		Flight flight = airlineDao.fetchFlight(booking);

		if (booking.getTravelClass().equalsIgnoreCase("economy")) {
			flight.setEconomySeats(flight.getEconomySeats() - confirmedSeats);
		} else {
			flight.setBusinessSeats(flight.getBusinessSeats() - confirmedSeats);
		}
		flight = (Flight) dao.save(flight);
		
		Payment payment = new Payment();
		payment.setAmountPaid(booking.getCost());
		payment.setPaymentMode("Net Banking / UPI");
		payment.setBooking(booking);
		payment = (Payment) dao.save(payment);
	}

}
