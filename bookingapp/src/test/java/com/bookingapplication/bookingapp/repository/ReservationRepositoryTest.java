package com.bookingapplication.bookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bookingapplication.bookingapp.repositoryjpa.AccommodationReservationRepositoryJpa;

@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {
	@Autowired
	private AccommodationReservationRepositoryJpa reservationRepository;
}
