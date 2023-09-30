package com.bus.adminservice.entity;
import javax.persistence.*;

@Entity
@Table(name = "bus_routes")
public class BusRoute {
    @Id
    @Column(name = "bus_number")
    private String busNumber;

    private String source;

    private String destination;

    private double price;
    
    @Column(name = "total_seat")
    private Integer totalNoOfSeat;

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getTotalNoOfSeat() {
		return totalNoOfSeat;
	}

	public void setTotalNoOfSeat(Integer totalNoOfSeat) {
		this.totalNoOfSeat = totalNoOfSeat;
	}

	public BusRoute(String busNumber, String source, String destination, double price, Integer totalNoOfSeat) {
		super();
		this.busNumber = busNumber;
		this.source = source;
		this.destination = destination;
		this.price = price;
		this.totalNoOfSeat = totalNoOfSeat;
	}

	public BusRoute() {
		super();
		// TODO Auto-generated constructor stub
	}


}
