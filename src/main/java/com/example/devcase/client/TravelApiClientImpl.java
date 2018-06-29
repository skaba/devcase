package com.example.devcase.client;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.devcase.model.Airport;
import com.example.devcase.model.Currency;
import com.example.devcase.model.EnhancedFare;
import com.example.devcase.model.Fare;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TravelApiClientImpl implements TravelApiClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${travelapi.url}")
	private String travelApiUrl;

	@Override
	public EnhancedFare getFare(String origin, String destination, Currency currency) {
		CompletableFuture<Fare> fareFuture = supplyAsync(() -> retrieveFare(origin, destination, currency));
		CompletableFuture<Airport> originFuture = supplyAsync(() -> retrieveAirport(origin));
		CompletableFuture<Airport> destinationFuture = supplyAsync(() -> retrieveAirport(destination));
		allOf(fareFuture, originFuture, destinationFuture).join();
		try {
			EnhancedFare enhancedFare = new EnhancedFare();
			enhancedFare.setAmount(fareFuture.get().getAmount());
			enhancedFare.setCurrency(fareFuture.get().getCurrency());
			enhancedFare.setOrigin(originFuture.get());
			enhancedFare.setDestination(destinationFuture.get());
			return enhancedFare;
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
			throw new ClientException();
		}

	}

	@Override
	public void doGet(final HttpServletRequest request, HttpServletResponse response) throws IOException {
		final URI uri = UriComponentsBuilder.fromHttpUrl(travelApiUrl)
				.replacePath(StringUtils.substringAfter(request.getRequestURI(), "travelapi"))
				.replaceQuery(request.getQueryString()).build().toUri();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(responseEntity.getStatusCodeValue());
		response.getWriter().append(responseEntity.getBody());
	}

	private Fare retrieveFare(String origin, String destination, Currency currency) {
		log.info("retrieveFare {},{},{}", origin, destination, currency);
		return restTemplate.getForObject(travelApiUrl + "/fares/{origin}/{destination}?currency={currency}", Fare.class,
				origin, destination, currency);
	}

	private Airport retrieveAirport(String airportCode) {
		log.info("retrieveAirport {}", airportCode);
		return restTemplate.getForObject(travelApiUrl + "/airports/{airportCode}", Airport.class, airportCode);
	}

}
