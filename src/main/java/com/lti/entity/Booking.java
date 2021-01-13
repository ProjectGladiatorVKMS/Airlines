package com.lti.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	@Column(name = "booking_id")
	private int bookingId;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;

	@Column(name = "departure_from_source")
	private String departure;

	@Column(name = "arrival_at_destination")
	private String arrival;

	@Column(name = "journey_date")
	private Date journeyDate;

	@Column(name = "no_of_passengers")
	private int noOfPassengers;

	@Column(name = "cost")
	private double cost;

	@Column(name = "ticket_mailing_id")
	private String ticketMailingId;

	@OneToOne
	@JoinColumn(name = "user_id_fk")
	private User user;

	@OneToOne
	@JoinColumn(name = "flight_id_fk")
	private Flight flight;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private Set<Passenger> passengerList;

	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
	private Payment payment;

	public Booking() {
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getTicketMailingId() {
		return ticketMailingId;
	}

	public void setTicketMailingId(String ticketMailingId) {
		this.ticketMailingId = ticketMailingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Set<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(Set<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
