package com.bus.adminservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bus_inventory")
public class BusInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

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

	public BusInventory(String busNumber, int availableSeats, Date lastUpdatedDate) {
		super();
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public BusInventory() {
		super();
		// TODO Auto-generated constructor stub
	}

}
