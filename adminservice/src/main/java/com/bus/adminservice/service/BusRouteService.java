package com.bus.adminservice.service;

import java.util.List;
import java.util.Optional;

import com.bus.adminservice.entity.BusRoute;

public interface BusRouteService {
	public List<BusRoute> getAllBusRoutes();
	public Optional<BusRoute> getBusRouteByBusNumber(String busNumber);
	public BusRoute addBusRoute(BusRoute busRoute);
	public void updateBusRoute(BusRoute busRoute) ;
	public void deleteBusRouteByBusNumber(String busNumber) ;

}
