package com.bus.paymentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.bus.paymentservice.entity.Booking;
import com.bus.paymentservice.entity.Payment;
import com.bus.paymentservice.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	JmsTemplate jmsTemplate;

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Optional<Payment> getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId);
	}

	public Payment addPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public void updatePayment(Payment payment) {
		paymentRepository.save(payment);
	}

	public void deletePaymentById(Long paymentId) {
		paymentRepository.deleteById(paymentId);
	}

	@JmsListener(destination = "bus_booking_created_event")
	public void processPayment(String bookingJson) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Booking booking = objectMapper.readValue(bookingJson, Booking.class);
			Payment payment = new Payment();
			payment.setBookingNumber(booking.getBookingNumber());
			payment.setPaymentDate(new java.sql.Date(new java.util.Date().getTime()));
			paymentRepository.save(payment);
			jmsTemplate.convertAndSend("bus_payment_created_event", bookingJson);
		} catch (Exception e) {
			throw new RuntimeException("Error while processing payment");
		}

	}

	@JmsListener(destination = "bus_booking_inventory_failed_event")
	public void receiveInventoryFailedEvent(@Payload Booking booking) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Payment payment = paymentRepository.findPaymentByBookingNumber(booking.getBookingNumber()).get(0);
		paymentRepository.delete(payment);
		jmsTemplate.convertAndSend("bus_booking_payment_failed_event", booking);
	}

}
