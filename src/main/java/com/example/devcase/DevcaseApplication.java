package com.example.devcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableAutoConfiguration
@EnableOAuth2Client
public class DevcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevcaseApplication.class, args);
	}

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(final ClientCredentialsResourceDetails resource,
			final OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}
}
