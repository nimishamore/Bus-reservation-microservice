package com.bus.bookingservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id", unique = true)
    private Long passenger_id;

    @Column(name = "booking_number", unique = true)
    private String bookingNumber;

	public Long getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(Long passenger_id) {
		this.passenger_id = passenger_id;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public Passenger(Long passenger_id, String bookingNumber) {
		super();
		this.passenger_id = passenger_id;
		this.bookingNumber = bookingNumber;
	}

	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
