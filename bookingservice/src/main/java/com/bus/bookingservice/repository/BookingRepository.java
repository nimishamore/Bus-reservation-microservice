package com.bus.bookingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.bookingservice.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	  Optional<Booking> findByBookingNumber(String bookingNumber);

	  void deleteByBookingNumber(String bookingNumber);
}
