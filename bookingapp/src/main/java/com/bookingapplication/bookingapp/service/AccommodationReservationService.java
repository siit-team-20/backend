package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;

public interface AccommodationReservationService {

	Collection<AccommodationReservation> findAll();

	AccommodationReservation findOne(Long id);

	AccommodationReservation create(AccommodationReservation reservationRequest) throws Exception;

	AccommodationReservation update(AccommodationReservation reservationRequest) throws Exception;

	void delete(Long id);
}
