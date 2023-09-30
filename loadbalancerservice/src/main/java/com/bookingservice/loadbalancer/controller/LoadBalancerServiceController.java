package com.bookingservice.loadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookingservice.loadbalancer.entity.Booking;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class LoadBalancerServiceController {

	@Autowired
	LoadBalancerClientFactory clientFactory;

	@Autowired
	RestTemplate restTemplate;

	@CircuitBreaker(name = "backup", fallbackMethod = "fallbackGetOrders")
	@GetMapping("/bookings")
	public String getBookings() {

		RoundRobinLoadBalancer lb = clientFactory.getInstance("BOOKINGSERVICE", RoundRobinLoadBalancer.class);

		ServiceInstance instance = lb.choose().block().getServer();

		String host = instance.getHost();
		int port = instance.getPort();

		String url = "http://" + host + ":" + port + "/orders";
		System.out.println(url);
		String response = restTemplate.getForObject(url, String.class);
		return response;

	}

	@PostMapping("/bookings")
	public String createOrder(@RequestBody Booking booking) {
		RoundRobinLoadBalancer lb = clientFactory.getInstance("BOOKINGSERVICE", RoundRobinLoadBalancer.class);

		ServiceInstance instance = lb.choose().block().getServer();
		String host = instance.getHost();
		int port = instance.getPort();
		String url = "http://" + host + ":" + port + "/orders";
		System.out.println(url);
		ResponseEntity<String> response = restTemplate.postForEntity(url, booking, String.class);
		return response.getBody();
	}

	public String fallbackGetOrders(CallNotPermittedException ex) {
		return "response from fallback service";
	}

}
