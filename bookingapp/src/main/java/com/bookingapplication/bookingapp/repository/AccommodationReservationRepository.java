package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;

public interface AccommodationReservationRepository {
	
	Collection<AccommodationReservationDTO> findAll();
	AccommodationReservationDTO create(AccommodationReservationDTO accommodationReservation);
	AccommodationReservationDTO findOne(Long id);
	AccommodationReservationDTO update(AccommodationReservationDTO accommodationReservation);
	void delete(Long id);
}
