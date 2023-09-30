package com.bus.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.bus.inventoryservice.entity.Booking;
import com.bus.inventoryservice.entity.BusInventory;
import com.bus.inventoryservice.repository.BusInventoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BusInventoryServiceImpl implements BusInventoryService {

	@Autowired
	BusInventoryRepository busInventoryRepository;

	@Autowired
	JmsTemplate jmsTemplate;

	public List<BusInventory> getAllBusInventory() {
		return busInventoryRepository.findAll();
	}

	public Optional<BusInventory> getBusInventoryByBusNumber(String busNumber) {
		return busInventoryRepository.findById(busNumber);
	}

	public BusInventory addBusInventory(BusInventory busInventory) {
		return busInventoryRepository.save(busInventory);
	}

	public void updateBusInventory(BusInventory busInventory) {
		busInventoryRepository.save(busInventory);
	}

	public void deleteBusInventoryByBusNumber(String busNumber) {
		busInventoryRepository.deleteById(busNumber);
	}

	@JmsListener(destination = "bus_payment_created_event")
	public void receiveBusBooking(String bookingJson) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Booking booking = objectMapper.readValue(bookingJson, Booking.class);
			List<BusInventory> busInventoryList = busInventoryRepository
					.findInventoryByBusNumber(booking.getBusNumber());
			for(BusInventory busInventory : busInventoryList) {
				if(busInventory != null && busInventory.getBusNumber().equalsIgnoreCase(booking.getBusNumber())) {
					int availableSeats = busInventory.getAvailableSeats();
					if (availableSeats <= 0) {
						throw new Exception("seat not available");
					}
					availableSeats = availableSeats - booking.getNumberOfSeats();
					busInventory.setAvailableSeats(availableSeats);
					busInventory.setBusNumber(busInventory.getBusNumber());
					busInventory.setLastUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));
					busInventoryRepository.save(busInventory);
					jmsTemplate.convertAndSend("bus_inventoy_updated_event", bookingJson);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			jmsTemplate.convertAndSend("bus_inventoy_failed_event", bookingJson);
		}
	}

}
