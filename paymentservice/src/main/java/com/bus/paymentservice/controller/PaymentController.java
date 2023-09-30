package com.bus.paymentservice.controller;

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

import com.bus.paymentservice.entity.Payment;
import com.bus.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = paymentService.getAllPayments();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
		Optional<Payment> payment = paymentService.getPaymentById(paymentId);
		return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
		Payment newPayment = paymentService.addPayment(payment);
		return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
	}

	@PutMapping("/{paymentId}")
	public ResponseEntity<Void> updatePayment(@PathVariable Long paymentId, @RequestBody Payment payment) {
		Optional<Payment> existingPayment = paymentService.getPaymentById(paymentId);
		if (existingPayment.isPresent()) {
			payment.setPaymentId(paymentId);
			paymentService.updatePayment(payment);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePaymentById(@PathVariable Long paymentId) {
		Optional<Payment> existingPayment = paymentService.getPaymentById(paymentId);
		if (existingPayment.isPresent()) {
			paymentService.deletePaymentById(paymentId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
