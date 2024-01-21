package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.ReservationStatus;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;

public interface AccommodationReservationService {

	Collection<ReservationWithAccommodationDTO> findAll();
	Collection<ReservationWithAccommodationDTO> findAll(String guestEmail);
	Collection<ReservationWithAccommodationDTO> findAll(String guestEmail, ReservationStatus status);
	Collection<ReservationWithAccommodationDTO> findAll(String guestEmail, ReservationStatus status, Long days);
	Collection<ReservationWithAccommodationDTO> findAllByOwnerEmail(String ownerEmail);
	Collection<ReservationWithAccommodationDTO> findAll(String ownerEmail, String guestEmail, ReservationStatus status);
	AccommodationReservationDTO findOne(Long id);
	AccommodationReservationDTO create(AccommodationReservationDTO reservationRequest) throws Exception;
	AccommodationReservationDTO update(AccommodationReservationDTO reservationRequest, Long id) throws Exception;
	void delete(Long id);
	void updateStatuses();

	AccommodationReservation toAccommodationReservation(AccommodationReservationDTO accommodationReservationDTO);

	AccommodationReservationDTO toAccommodationReservationDTO(AccommodationReservation accommodationReservation);

    List<AccommodationReservationDTO> toAccommodationReservationDtos(List<AccommodationReservation> accommodationReservations);

    void updateAccommodationReservation(AccommodationReservation target, AccommodationReservation source);
    
    // ---------------------------------
    
    AccommodationReservation toAccommodationReservation(ReservationWithAccommodationDTO reservationWithAccommodationDTO);

    ReservationWithAccommodationDTO toReservationWithAccommodationDTO(AccommodationReservation accommodationReservation);

    List<ReservationWithAccommodationDTO> toReservationWithAccommodationDtos(List<AccommodationReservation> accommodationReservations);

}
