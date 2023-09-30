package com.bus.bookingservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bus.bookingservice.dao.BusInventoryResponse;
import com.bus.bookingservice.entity.Booking;
import com.bus.bookingservice.entity.Passenger;
import com.bus.bookingservice.repository.BookingRepository;
import com.bus.bookingservice.repository.PassengerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookingServiceImpl implements BookingService {

	private static final String INVENTORY_SERVICE_URL = "http://localhost:8024/bus-inventory/";

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public Optional<Booking> getBookingByNumber(String bookingNumber) {
		return bookingRepository.findByBookingNumber(bookingNumber);
	}

	@Override
	public void updateBooking(Booking booking) {
		bookingRepository.save(booking);
	}

	@Override
	public void deleteBookingByNumber(String bookingNumber) {
		bookingRepository.deleteByBookingNumber(bookingNumber);
	}

	@Override
	public Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	public Integer checkAvailableSeats(String busNumber) {
		ResponseEntity<BusInventoryResponse> response = restTemplate.getForEntity(INVENTORY_SERVICE_URL + busNumber,
				BusInventoryResponse.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			BusInventoryResponse inventoryResponse = response.getBody();

			if (inventoryResponse != null) {
				return inventoryResponse.getAvailableSeats();
			} else {
				throw new RuntimeException("Invalid response received from inventory service");
			}
		} else {
			throw new RuntimeException(
					"Error while checking seat availability. Status code: " + response.getStatusCode());
		}
	}

	public void makeBooking(Booking bookings) {
		ResponseEntity<BusInventoryResponse> response = restTemplate
				.getForEntity(INVENTORY_SERVICE_URL + bookings.getBusNumber(), BusInventoryResponse.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			BusInventoryResponse inventory = response.getBody();
			if (inventory.getAvailableSeats() >= bookings.getNumberOfSeats()) {
				bookings.setStatus("Pending");
				bookingRepository.save(bookings);
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					String bookingJson = objectMapper.writeValueAsString(bookings);
					jmsTemplate.convertAndSend("bus_booking_created_event", bookingJson);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				throw new RuntimeException("Insufficient seats available");
			}
		} else {
			throw new RuntimeException("Error while checking seat availability");
		}
	}

	@JmsListener(destination="bus_inventoy_updated_event")
	public void processBookingConfirmation(String bookingJson) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Booking booking = objectMapper.readValue(bookingJson, Booking.class);
			booking.setStatus("confirmed");
			bookingRepository.save(booking);
			// Update passenger details in the booking table
			Passenger passengers = new Passenger();
			passengers.setBookingNumber(booking.getBookingNumber());
			passengerRepository.save(passengers);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@JmsListener(destination="bus_payment_failed_event")
	public void receivePaymentFailure(@Payload Booking booking) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		booking.setStatus(booking.getStatus());
		bookingRepository.save(booking);
	}

}
