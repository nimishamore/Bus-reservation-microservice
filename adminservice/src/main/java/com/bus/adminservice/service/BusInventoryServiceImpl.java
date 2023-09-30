package com.bus.adminservice.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bus.adminservice.entity.BusInventory;
import com.bus.adminservice.repository.BusInventoryRepository;

public class BusInventoryServiceImpl implements BusInventoryService {

	@Autowired
	BusInventoryRepository busInventoryRepository;

	public int getTotalSeatsAvailable(String busNumber) {
		Optional<BusInventory> optionalBusInventory = busInventoryRepository.findByBusNumber(busNumber);
		return optionalBusInventory.map(BusInventory::getAvailableSeats).orElse(0);
	}

	public void updateInventory(String busNumber, int allocatedSeats) {
		Optional<BusInventory> optionalBusInventory = busInventoryRepository.findByBusNumber(busNumber);
		optionalBusInventory.ifPresent(busInventory -> {
			int availableSeats = busInventory.getAvailableSeats();
			int updatedAvailableSeats = availableSeats - allocatedSeats;
			busInventory.setAvailableSeats(updatedAvailableSeats);
			busInventory.setLastUpdatedDate(new Date());
			busInventoryRepository.save(busInventory);
		});

	}
}