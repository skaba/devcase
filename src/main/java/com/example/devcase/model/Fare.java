package com.example.devcase.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Fare {
	private BigDecimal amount;
	private Currency currency;
	private String origin;
	private String destination;
}
