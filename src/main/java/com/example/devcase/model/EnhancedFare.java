package com.example.devcase.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EnhancedFare {
	private BigDecimal amount;
	private Currency currency;
	private Airport origin;
	private Airport destination;
}
