package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;

public interface AccommodationRepository {
	
	Collection<AccommodationDTO> findAll();
	AccommodationDTO findOne(Long id);
	AccommodationDTO create(AccommodationDTO accommodation);
	AccommodationDTO update(AccommodationDTO accommodation);
	void delete(Long id);

}
