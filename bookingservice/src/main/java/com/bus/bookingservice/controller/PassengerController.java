package com.bus.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bookingservice.entity.Passenger;
import com.bus.bookingservice.service.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
		
		@Autowired
	    PassengerService passengerService;
	    
	    @GetMapping
	    public ResponseEntity<List<Passenger>> getAllPassengerInfo() {
	        List<Passenger> passengers = passengerService.getAllPassengerDetials();
	        return new ResponseEntity<>(passengers, HttpStatus.OK);
	    }
}
