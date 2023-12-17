package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;

public interface AccommodationRequestRepository {

	Collection<AccommodationRequestDTO> findAll();

	AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequest);

	AccommodationRequestDTO findOne(Long id);
	
	AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequest);

	void delete(Long id);
	
}
