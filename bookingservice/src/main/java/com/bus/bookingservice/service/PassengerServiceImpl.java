package com.bus.bookingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.bookingservice.entity.Passenger;
import com.bus.bookingservice.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Override
	public List<Passenger> getAllPassengerDetials() {
		return passengerRepository.findAll();
	}

}
