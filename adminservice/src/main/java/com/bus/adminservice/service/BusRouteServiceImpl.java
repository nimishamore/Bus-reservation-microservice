package com.bus.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.adminservice.entity.BusRoute;
import com.bus.adminservice.repository.BusRouteRepository;

@Service
public class BusRouteServiceImpl implements BusRouteService {

	@Autowired
	BusRouteRepository busRouteRepository;

	
	public List<BusRoute> getAllBusRoutes() {
		return busRouteRepository.findAll();
	}

	public Optional<BusRoute> getBusRouteByBusNumber(String busNumber) {
		return busRouteRepository.findByBusNumber(busNumber);
	}

	public BusRoute addBusRoute(BusRoute busRoute) {
		return busRouteRepository.save(busRoute);
	}

	public void updateBusRoute(BusRoute busRoute) {
		busRouteRepository.save(busRoute);
	}

	public void deleteBusRouteByBusNumber(String busNumber) {
		busRouteRepository.deleteByBusNumber(busNumber);
	}
	
	
	
}
