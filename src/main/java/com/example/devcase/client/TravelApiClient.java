package com.example.devcase.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.devcase.model.Currency;
import com.example.devcase.model.EnhancedFare;

public interface TravelApiClient {
	EnhancedFare getFare(String origin, String destination, Currency currency);

	void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
