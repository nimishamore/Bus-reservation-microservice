package com.bus.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.bookingservice.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
