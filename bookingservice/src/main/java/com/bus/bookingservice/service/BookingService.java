package com.bus.bookingservice.service;

import java.util.List;
import java.util.Optional;

import com.bus.bookingservice.entity.Booking;

public interface BookingService {

	public List<Booking> getAllBooking();

	public Optional<Booking> getBookingByNumber(String bookingNumber);

	public Booking addBooking(Booking booking);

	public void updateBooking(Booking booking);

	public void deleteBookingByNumber(String bookingNumber);

	public Integer checkAvailableSeats(String busNumber);

	public void makeBooking(Booking booking);
}
