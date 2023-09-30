package com.bus.paymentservice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.paymentservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("FROM Payment where bookingNumber=?1")
	public List<Payment> findPaymentByBookingNumber(String bookingNumber);
}


