package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminLoginDataTransfer;
import com.lti.dto.BookingDT;
import com.lti.dto.FlightDataTransfer;
import com.lti.dto.LoginStatusDT;
import com.lti.dto.SearchFlightsDT;
import com.lti.dto.StatusDT;
import com.lti.dto.TicketDT;
import com.lti.dto.UserLoginDataTransfer;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.User;
import com.lti.service.AdminService;
import com.lti.service.AirlineService;
import com.lti.service.LoginService;

@RestController
@CrossOrigin
public class AirlineController {

//	@Autowired
//	private AdminService adminService;

	@Autowired
	private AirlineService airlineService;

//	@Autowired
//	private LoginService loginService;

	/*----------------------------------------------------------Login Validation------------------------------------------------------------------*/
//	@PostMapping("/validateUser")
//	public LoginStatusDT validateUser(@RequestBody UserLoginDataTransfer userLoginDT) {
//		LoginStatusDT loginStatus = loginService.validateUser(userLoginDT.getEmailId(), userLoginDT.getPassword());
//		return loginStatus;
//	}
//
//	@PostMapping("/validateAdmin")
//	public LoginStatusDT validateAdmin(@RequestBody AdminLoginDataTransfer adminLoginDT) {
//		LoginStatusDT loginStatus = loginService.validateAdmin(adminLoginDT.getUsername(), adminLoginDT.getPassword());
//		return loginStatus;
//	}

	/*----------------------------------------------------------Admin Controller------------------------------------------------------------------*/
//	@PostMapping("/addFlight")
//	public String addFlight(@RequestBody FlightDataTransfer flightDT) {
//		System.out.println("Airline controller");
//		Flight flight = new Flight();
//		Admin admin = new Admin();
//		admin.setAdminId(flightDT.getAdminId());
//
//		flight.setSource(flightDT.getSource());
//		flight.setDestination(flightDT.getDestination());
//		flight.setJourneyDate(flightDT.getJourneyDate());
//		flight.setDeparture(flightDT.getDepartureTime());
//		flight.setArrival(flightDT.getArrivalTime());
//		flight.setEconomyClassCost(flightDT.getEconomyClassCost());
//		flight.setBusinessClassCost(flightDT.getBusinessClassCost());
//		flight.setNoOfSeats(flightDT.getNoOfSeats());
//		flight.setEconomySeats(flightDT.getEconomySeats());
//		flight.setBusinessSeats(flightDT.getBusinessSeats());
//		flight.setAdmin(admin);
//		System.out.println("Airline controller");
//		adminService.addFlight(flight);
//
//		return null;
//	}
//
//	@DeleteMapping("/deleteFlight")
//	public String deleteFlight(@RequestParam("id") int FlightId) {
//		adminService.deleteFight(FlightId);
//		return null;
//	}
//
//	@GetMapping("/displayAllFlights")
//	public List<Flight> displayAllFlights() {
//		List<Flight> flightList = adminService.displayAllFlights();
//		return flightList;
//	}
//
//	@GetMapping("/displayFlight")
//	public Flight displayFlight(@RequestParam("id") int flightId) {
//		System.out.println(flightId);
//		Flight flight = adminService.displayFlight(flightId);
//		System.out.println(flight);
//		return flight;
//	}

	/*
	 * @GetMapping("/hello") public String hello(@RequestParam("name") String name)
	 * { return "Hello "+name; }
	 */

	/*-------------------------------------------------------------- User Controller------------------------------------------------------------*/

	@PostMapping("/registerUser")
	public StatusDT registerUser(@RequestBody User user) {
		int user_id = airlineService.registerUser(user);
		StatusDT status = new StatusDT();
		status.setGeneratedId(user_id);
		status.setMessage("User Registered Successfully");
		return status;
	}

	@PostMapping("/searchFlights")
	public List<Flight> searchFlights(@RequestBody SearchFlightsDT searchFlightsDT) {
		return airlineService.searchFlights(searchFlightsDT);
	}

	@PostMapping("/addBooking")
	public StatusDT addBooking(@RequestBody BookingDT bookingDT) {
		int bookingId = airlineService.addBooking(bookingDT);
		StatusDT status = new StatusDT();
		status.setGeneratedId(bookingId);
		status.setMessage("Ticket Booking in Progress");
		return status;
	}

	@PostMapping("/updateBooking")
	public void updateBooking(@RequestParam("bookingId") int bookingId) {
		airlineService.updateBooking(bookingId);
		System.out.println(bookingId);
	}

	@GetMapping("/fetchTicket")
	public TicketDT fetchTicket(@RequestParam("bookingId") int bookingId) {

		TicketDT ticket = airlineService.fetchTicket(bookingId);
		return ticket;
	}
	
	

}