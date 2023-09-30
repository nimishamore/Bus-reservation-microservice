package com.bus.inventoryservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.inventoryservice.entity.BusInventory;
import com.bus.inventoryservice.service.BusInventoryService;

@RestController
@RequestMapping("/bus-inventory")
public class BusInventoryController {

	@Autowired
	BusInventoryService busInventoryService;

	@GetMapping
	public ResponseEntity<List<BusInventory>> getAllBusInventory() {
		List<BusInventory> busInventoryList = busInventoryService.getAllBusInventory();
		return new ResponseEntity<>(busInventoryList, HttpStatus.OK);
	}

	@GetMapping("/{busNumber}")
	public ResponseEntity<BusInventory> getBusInventoryByBusNumber(@PathVariable String busNumber) {
		Optional<BusInventory> busInventory = busInventoryService.getBusInventoryByBusNumber(busNumber);
		return busInventory.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<BusInventory> addBusInventory(@RequestBody BusInventory busInventory) {
		BusInventory newBusInventory = busInventoryService.addBusInventory(busInventory);
		return new ResponseEntity<>(newBusInventory, HttpStatus.CREATED);
	}

	@PutMapping("/{busNumber}")
	public ResponseEntity<Void> updateBusInventory(@PathVariable String busNumber,
			@RequestBody BusInventory busInventory) {
		Optional<BusInventory> existingBusInventory = busInventoryService.getBusInventoryByBusNumber(busNumber);
		if (existingBusInventory.isPresent()) {
			busInventory.setBusNumber(busNumber);
			busInventoryService.updateBusInventory(busInventory);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/update")
    public ResponseEntity<String> updateInventory(String bookingjson) {
        try {
        	busInventoryService.receiveBusBooking(bookingjson);
            return ResponseEntity.ok("Inventory updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
	
}