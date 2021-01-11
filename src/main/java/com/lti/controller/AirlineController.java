package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.service.AdminService;

@RestController
public class AirlineController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/addFlight")
	public String addFlight(@RequestBody Flight flight) {
		//Admin admin = new Admin();
		// admin.setAdminId(flight.getAdmin().getAdminId());
		//admin.setAdminId(1);
		//flight.setAdmin(admin);
		adminService.addFlight(flight);

		return null;
	}

	@DeleteMapping("/deleteFlight")
	public String deleteFlight(@RequestParam("id") int FlightId) {
		adminService.deleteFight(FlightId);
		return null;
	}

	@GetMapping("/displayAllFlights")
	public List<Flight> displayAllFlights() {
		List<Flight> flightList = adminService.displayAllFlights();
		return flightList;
	}

	@GetMapping("/displayFlight")
	public Flight displayFlight(@RequestParam("id") int flightId) {
		System.out.println(flightId);
		Flight flight = adminService.displayFlight(flightId);
		System.out.println(flight.toString());
		return flight;
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		return "Hello "+name;
	}
	
	
}
