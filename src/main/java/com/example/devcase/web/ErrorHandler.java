/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.example.devcase.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value = HttpStatusCodeException.class)
	public ResponseEntity<String> handleRestClientException(final HttpStatusCodeException exception) {
		return new ResponseEntity<>(exception.getResponseBodyAsString(), exception.getStatusCode());
	}
}
