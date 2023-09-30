package com.bus.bookingservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_number", unique = true)
    private String bookingNumber;

	@Column(name = "bus_number")
    private String busNumber;

    @Column(name = "booking_date")
    private Date bookingDate;

    private String source;

    private String destination;

    @Column(name = "num_of_seats")
    private int numberOfSeats;
    
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
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

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Booking(Long id, String bookingNumber, String busNumber, Date bookingDate, String source, String destination,
			int numberOfSeats,String status) {
		super();
		this.id = id;
		this.bookingNumber = bookingNumber;
		this.busNumber = busNumber;
		this.bookingDate = bookingDate;
		this.source = source;
		this.destination = destination;
		this.numberOfSeats = numberOfSeats;
		this.status = status;
	}

	  public Booking() {
			super();
			// TODO Auto-generated constructor stub
		}
 
}
