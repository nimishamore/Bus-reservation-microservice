package com.bus.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.adminservice.entity.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute, String> {

	Optional<BusRoute> findByBusNumber(String busNumber);

	void deleteByBusNumber(String busNumber);
}
