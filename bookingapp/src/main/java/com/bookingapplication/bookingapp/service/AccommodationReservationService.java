package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;

public interface AccommodationReservationService {

	Collection<AccommodationReservationDTO> findAll();
	Collection<AccommodationReservationDTO> findAll(String guestEmail);
	AccommodationReservationDTO findOne(Long id);
	AccommodationReservationDTO create(AccommodationReservationDTO reservationRequest) throws Exception;
	AccommodationReservationDTO update(AccommodationReservationDTO reservationRequest, Long id) throws Exception;
	void delete(Long id);

	AccommodationReservation toAccommodationReservation(AccommodationReservationDTO accommodationReservationDTO);

	AccommodationReservationDTO toAccommodationReservationDTO(AccommodationReservation accommodationReservation);

    List<AccommodationReservationDTO> toAccommodationReservationDtos(List<AccommodationReservation> accommodationReservations);

    void updateAccommodationReservation(AccommodationReservation target, AccommodationReservation source);
}
