package com.bus.paymentservice.service;

import java.util.List;
import java.util.Optional;

import com.bus.paymentservice.entity.Payment;

public interface PaymentService {
	public List<Payment> getAllPayments();

	public Optional<Payment> getPaymentById(Long paymentId);

	public Payment addPayment(Payment payment);

	public void updatePayment(Payment payment);

	public void deletePaymentById(Long paymentId);
	public void processPayment(String bookingJson);
}
