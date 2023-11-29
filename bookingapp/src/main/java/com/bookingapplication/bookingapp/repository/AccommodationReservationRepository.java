package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;

public interface AccommodationReservationRepository {
	
	Collection<AccommodationReservation> findAll();

	AccommodationReservation create(AccommodationReservation reservationRequest);

	AccommodationReservation findOne(Long id);
	
	AccommodationReservation update(AccommodationReservation reservationRequest);

	void delete(Long id);
}
