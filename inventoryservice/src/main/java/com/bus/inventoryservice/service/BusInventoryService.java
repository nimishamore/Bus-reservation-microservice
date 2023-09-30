package com.bus.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import com.bus.inventoryservice.entity.BusInventory;

public interface BusInventoryService {
	public List<BusInventory> getAllBusInventory();
	public Optional<BusInventory> getBusInventoryByBusNumber(String busNumber);
	public BusInventory addBusInventory(BusInventory busInventory);
	public void updateBusInventory(BusInventory busInventory);
	public void deleteBusInventoryByBusNumber(String busNumber) ;
	public void receiveBusBooking(String bookingJson);
	
}
