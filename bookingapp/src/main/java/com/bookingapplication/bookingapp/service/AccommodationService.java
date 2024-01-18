package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;

public interface AccommodationService {
	
	Collection<AccommodationDTO> findAll();
	Collection<AccommodationDTO> findAll(String ownerEmail);
	Collection<AccommodationDTO> findAll(boolean onlyApproved);
	AccommodationDTO findOne(Long id);
	AccommodationDTO create(AccommodationDTO accommodation) throws Exception;
	AccommodationDTO update(AccommodationDTO accommodation, Long id) throws Exception;
	void delete(Long id);
	
	Accommodation toAccommodation(AccommodationDTO accommodationDTO);

	AccommodationDTO toAccommodationDTO(Accommodation accommodation);

    List<AccommodationDTO> toAccommodationDtos(List<Accommodation> accommodations);

    void updateAccommodation(Accommodation target, Accommodation source);

}
