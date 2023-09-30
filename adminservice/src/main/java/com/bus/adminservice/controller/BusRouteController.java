package com.bus.adminservice.controller;
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

import com.bus.adminservice.entity.BusRoute;
import com.bus.adminservice.service.BusRouteService;

@RestController
@RequestMapping("/bus-routes")
public class BusRouteController {

    @Autowired
    BusRouteService busRouteService;

    @GetMapping
    public ResponseEntity<List<BusRoute>> getAllBusRoutes() {
        List<BusRoute> busRoutes = busRouteService.getAllBusRoutes();
        return new ResponseEntity<>(busRoutes, HttpStatus.OK);
    }

    @GetMapping("/{busNumber}")
    public ResponseEntity<BusRoute> getBusRouteByBusNumber(@PathVariable String busNumber) {
        Optional<BusRoute> busRoute = busRouteService.getBusRouteByBusNumber(busNumber);
        if (busRoute.isPresent()) {
            return new ResponseEntity<>(busRoute.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
        BusRoute newBusRoute = busRouteService.addBusRoute(busRoute);
        return new ResponseEntity<>(newBusRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{busNumber}")
    public ResponseEntity<Void> updateBusRoute(@PathVariable String busNumber, @RequestBody BusRoute busRoute) {
        Optional<BusRoute> existingBusRoute = busRouteService.getBusRouteByBusNumber(busNumber);
        if (existingBusRoute.isPresent()) {
            busRoute.setBusNumber(busNumber);
            busRouteService.updateBusRoute(busRoute);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{busNumber}")
    public ResponseEntity<Void> deleteBusRouteByBusNumber(@PathVariable String busNumber) {
        Optional<BusRoute> existingBusRoute = busRouteService.getBusRouteByBusNumber(busNumber);
        if (existingBusRoute.isPresent()) {
            busRouteService.deleteBusRouteByBusNumber(busNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
