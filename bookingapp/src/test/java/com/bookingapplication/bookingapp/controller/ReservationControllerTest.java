package com.bookingapplication.bookingapp.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.ResourceAccessException;

import com.bookingapplication.bookingapp.domain.ReservationStatus;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class ReservationControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String JWT_GUEST_1 = "";
	private String JWT_OWNER_1 = "";
	private String JWT_ADMIN = "";
	
	@BeforeAll
	public void setup() {
		login();
	}
	
	private String login(String email, char[] password) {
		final String URL = "/login";

		CredentialsDTO credentials = new CredentialsDTO(email, password);
		HttpEntity<CredentialsDTO> requestBody = new HttpEntity<CredentialsDTO>(credentials, getHeader(null));
		ResponseEntity<UserDTO> response = restTemplate.exchange(
				URL, HttpMethod.POST,requestBody,new ParameterizedTypeReference<UserDTO>() {}
		);
		
		return response.getBody().getToken();
	}
	
	public void login() {
		JWT_GUEST_1 = login("guest@gmail.com", "guest".toCharArray());
		JWT_OWNER_1 = login("owner@gmail.com", "owner".toCharArray());
		JWT_ADMIN = login("admin@gmail.com", "admin".toCharArray());
	}
	
	private HttpHeaders getHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (jwt != null) {
			headers.setBearerAuth(jwt);
		}
		
		return headers;
	}
	
	@Test
	public void acceptReservation_unauthorized() {
		final String URL = "/api/accommodations/reservations/{id}";
		Long reservationId = 3L;
		Assertions.assertThrows(ResourceAccessException.class, () -> {	
			HttpEntity<Void> requestBody = new HttpEntity<Void>(null, getHeader(null));
			ResponseEntity<AccommodationReservationDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);		
		});
	}
	
	@Test
	public void acceptReservation_notFound() {
		final String URL = "/api/accommodations/reservations/{id}";
		Long reservationId = 0L;	
		
		AccommodationReservationDTO reservation = new AccommodationReservationDTO();
		
		HttpEntity<AccommodationReservationDTO> requestBody = new HttpEntity<AccommodationReservationDTO>(reservation, getHeader(JWT_OWNER_1));
		ResponseEntity<AccommodationReservationDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void acceptReservation_forbidden_admin() {
		final String URL = "/api/accommodations/reservations/{id}";
		Long reservationId = 3L;	

		AccommodationReservationDTO reservation = new AccommodationReservationDTO();

		HttpEntity<AccommodationReservationDTO> requestBody = new HttpEntity<AccommodationReservationDTO>(reservation, getHeader(JWT_ADMIN));
		ResponseEntity<AccommodationReservationDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		
		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	
	@Test
	public void acceptReservation() {
		final String URL = "/api/accommodations/reservations/{id}";
		Long reservationId = 3L;

		HttpEntity<Void> header = new HttpEntity<Void>(null, getHeader(JWT_OWNER_1));
		ResponseEntity<AccommodationReservationDTO> res = restTemplate.exchange(URL, HttpMethod.GET, header, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		
		AccommodationReservationDTO reservation = res.getBody();
		reservation.setStatus(ReservationStatus.Approved);
		
		HttpEntity<AccommodationReservationDTO> requestBody = new HttpEntity<AccommodationReservationDTO>(reservation, getHeader(JWT_OWNER_1));
		ResponseEntity<AccommodationReservationDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		AccommodationReservationDTO result = response.getBody();
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		assertThat(result).usingRecursiveComparison().ignoringFields("status").isEqualTo(reservation);
		assertThat(result.getStatus()).isEqualTo(ReservationStatus.Approved);
	}
	
	@Test
	public void rejectReservation() {
		final String URL = "/api/accommodations/reservations/{id}";
		Long reservationId = 3L;

		HttpEntity<Void> header = new HttpEntity<Void>(null, getHeader(JWT_OWNER_1));
		ResponseEntity<AccommodationReservationDTO> res = restTemplate.exchange(URL, HttpMethod.GET, header, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		
		AccommodationReservationDTO reservation = res.getBody();
		reservation.setStatus(ReservationStatus.Rejected);
		
		HttpEntity<AccommodationReservationDTO> requestBody = new HttpEntity<AccommodationReservationDTO>(reservation, getHeader(JWT_OWNER_1));
		ResponseEntity<AccommodationReservationDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, new ParameterizedTypeReference<AccommodationReservationDTO>() {}, reservationId);	
		AccommodationReservationDTO result = response.getBody();
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		assertThat(result).usingRecursiveComparison().ignoringFields("status").isEqualTo(reservation);
		assertThat(result.getStatus()).isEqualTo(ReservationStatus.Rejected);
	}
}
