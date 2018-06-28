package com.example.devcase.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.devcase.client.TravelApiClient;

@Controller
public class TravelApiController {

	@Autowired
	private TravelApiClient client;

	@RequestMapping(value = "/travelapi/**", method = RequestMethod.GET)
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		client.doGet(request, response);
	}
}
