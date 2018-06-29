package com.example.devcase.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.devcase.client.TravelApiClient;
import com.example.devcase.model.Currency;
import com.example.devcase.model.EnhancedFare;

@RestController
public class FareController {

	@Autowired
	private TravelApiClient client;

	@RequestMapping(value = "/fares/{origin}/{destination}", method = RequestMethod.GET)
	public EnhancedFare getFare(@PathVariable("origin") String origin, @PathVariable("destination") String destination,
			@RequestParam(name = "currency", required = false, defaultValue = "EUR") Currency currency) {
		return client.getFare(origin, destination, currency);
	}
}
