package com.bus.inventoryservice.entity;


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

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public BusInventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusInventory( Long id,String busNumber, int availableSeats, Date lastUpdatedDate) {
		super();
		this.id=id;
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


    
}
