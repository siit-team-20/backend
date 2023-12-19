package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.dtos.AccommodationDTO;

public interface AccommodationService {
	
	Collection<AccommodationDTO> findAll();
	AccommodationDTO findOne(Long id);
	AccommodationDTO create(AccommodationDTO accommodation) throws Exception;
	AccommodationDTO update(AccommodationDTO accommodation, Long id) throws Exception;
	void delete(Long id);

}
