package com.bus.bookingservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bookingservice.dao.ApiResponse;
import com.bus.bookingservice.entity.Booking;
import com.bus.bookingservice.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getAllBooking();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@GetMapping("/{bookingNumber}")
	public ResponseEntity<Booking> getBookingByNumber(@PathVariable String bookingNumber) {
		Optional<Booking> booking = bookingService.getBookingByNumber(bookingNumber);
		if (booking.isPresent()) {
			return new ResponseEntity<>(booking.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	 * @PostMapping public ResponseEntity<Booking> addBooking(@RequestBody Booking
	 * booking) { Booking newBooking = bookingService.addBooking(booking); return
	 * new ResponseEntity<>(newBooking, HttpStatus.CREATED); }
	 */

	@PutMapping("/{bookingNumber}")
	public ResponseEntity<Void> updateBooking(@PathVariable String bookingNumber, @RequestBody Booking booking) {
		Optional<Booking> existingBooking = bookingService.getBookingByNumber(bookingNumber);
		if (existingBooking.isPresent()) {
			booking.setBookingNumber(bookingNumber);
			bookingService.updateBooking(booking);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{bookingNumber}")
	public ResponseEntity<Void> deleteBookingByNumber(@PathVariable String bookingNumber) {
		Optional<Booking> existingBooking = bookingService.getBookingByNumber(bookingNumber);
		if (existingBooking.isPresent()) {
			bookingService.deleteBookingByNumber(bookingNumber);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/checkAvailability/{busNumber}")
	public ResponseEntity<ApiResponse> checkAvailability(@PathVariable String busNumber) {
		System.out.println("busNumber: " + busNumber);
		int availableSeats = bookingService.checkAvailableSeats(busNumber);
		ApiResponse response = new ApiResponse(availableSeats + " no of seat are available.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> bookSeat(@RequestBody Booking booking) {
		try {
			bookingService.makeBooking(booking);
			return ResponseEntity.ok("Booking created with status... pending");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
