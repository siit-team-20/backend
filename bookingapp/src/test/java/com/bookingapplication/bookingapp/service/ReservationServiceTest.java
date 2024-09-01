package com.bookingapplication.bookingapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.ReservationStatus;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationReservationRepositoryJpa;
import com.bookingapplication.bookingapp.serviceImpl.AccommodationReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
	@Autowired
	@InjectMocks
	private AccommodationReservationServiceImpl reservationService;
	
	@Mock
	private AccommodationReservationRepositoryJpa reservationRepository;
	
	@Test
	@DisplayName("Accept reservation - status changed")
	public void acceptReservation() throws Exception {
		AccommodationReservation reservation = new AccommodationReservation();
		Mockito.when(this.reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));
		Mockito.when(this.reservationRepository.save(reservation)).thenReturn(reservation);
		
		reservation.setStatus(ReservationStatus.Approved);
		AccommodationReservationDTO r = reservationService.update(reservationService.toAccommodationReservationDTO(reservation), reservation.getId());
		Assertions.assertTrue(r.getStatus().equals(ReservationStatus.Approved));
	}
	
	@Test
	@DisplayName("Accept reservation - accommodation does not exist")
	public void acceptReservationAccommodationDoesNotExist() throws Exception {
		AccommodationReservation reservation = new AccommodationReservation();
		
		reservation.setStatus(ReservationStatus.Approved);
		AppException thrown = Assertions.assertThrows(AppException.class, () -> {
			reservationService.update(reservationService.toAccommodationReservationDTO(reservation), reservation.getId());
		});
		Assertions.assertTrue(thrown.getMessage().equals("Accommodation not found"));
		Assertions.assertTrue(thrown.getStatus().equals(HttpStatus.NOT_FOUND));
	}
}
