package com.bus.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.inventoryservice.entity.BusInventory;

@Repository
public interface BusInventoryRepository extends JpaRepository<BusInventory, Long> {
	
	@Query("From BusInventory where busNumber=?1")
	public List<BusInventory> findInventoryByBusNumber(String busNumber);
	
	
	@Query("From BusInventory where busNumber=?1")
	public Optional<BusInventory> findById(String busNumber);
	
	@Query("Delete From BusInventory where busNumber=?1")
	public void deleteById(String busNumber);
}
