package com.lti.dao;

import java.util.List;

import com.lti.dto.SearchFlightsDT;
import com.lti.dto.TicketDT;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;

public interface AirlineOperationDao {

	public List<Flight> searchFlightOperation(SearchFlightsDT searchFlightsDT);
	public void addPassenger(Passenger passenger);
	public List<Passenger> fetchPassenger(int bookingId);
	public Flight fetchFlight(Booking booking);
	public TicketDT fetchTicket(int bookingId);
	public double cancelTicket(int bookingId);
	public List<Booking> fetchBooking(int userId);
}
