package com.bus.adminservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.adminservice.entity.BusInventory;

public interface BusInventoryRepository extends JpaRepository<BusInventory, Long> {

	Optional<BusInventory> findByBusNumber(String busNumber);
}
