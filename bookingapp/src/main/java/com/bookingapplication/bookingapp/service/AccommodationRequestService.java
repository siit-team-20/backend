package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;

public interface AccommodationRequestService {

	Collection<AccommodationRequestDTO> findAll();

	AccommodationRequestDTO findOne(Long id);

	AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequest) throws Exception;

	AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequest) throws Exception;

	void delete(Long id);
	
}
