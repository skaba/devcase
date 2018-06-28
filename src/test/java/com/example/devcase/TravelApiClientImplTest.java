package com.example.devcase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.devcase.client.TravelApiClient;
import com.example.devcase.model.Currency;
import com.example.devcase.model.EnhancedFare;

@SpringBootTest(classes = DevcaseApplication.class)
@RunWith(SpringRunner.class)
public class TravelApiClientImplTest {
	@Autowired
	private TravelApiClient travelApiClient;

	@Test
	public void testGetFare() {
		EnhancedFare fare = travelApiClient.getFare("AMS", "JFK", Currency.EUR);
		assertThat(fare.getCurrency()).isEqualTo(Currency.EUR);
		assertThat(fare.getOrigin().getCode()).isEqualTo("AMS");
		assertThat(fare.getOrigin().getDescription()).isEqualTo("Amsterdam - Schiphol (AMS), Netherlands");
		assertThat(fare.getOrigin().getName()).isEqualTo("Schiphol");
		assertThat(fare.getDestination().getCode()).isEqualTo("JFK");
		assertThat(fare.getDestination().getDescription())
				.isEqualTo("New York - John F. Kennedy International (JFK), USA");
		assertThat(fare.getDestination().getName()).isEqualTo("John F. Kennedy International");
	}
}
