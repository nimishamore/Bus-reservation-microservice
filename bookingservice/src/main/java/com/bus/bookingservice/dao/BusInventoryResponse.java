package com.bus.bookingservice.dao;

import java.util.Date;

public class BusInventoryResponse {
	private int id;
    private String busNumber;
    private int availableSeats;
    private Date lastUpdatedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public BusInventoryResponse(int id, String busNumber, int availableSeats, Date lastUpdatedDate) {
		super();
		this.id = id;
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public BusInventoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



}
