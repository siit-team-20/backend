package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;

public interface AccommodationRequestService {

	Collection<AccommodationRequestDTO> findAll();

	AccommodationRequestDTO findOne(Long id);

	AccommodationRequestDTO create(AccommodationDTO accommodationDTO, AccommodationRequestType accommodationRequestType) throws Exception;
	
	AccommodationRequestDTO create(AccommodationDTO oldaccommodationDTO, AccommodationDTO newAccommodationDTO, AccommodationRequestType accommodationRequestType) throws Exception;
	
	AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequest) throws Exception;

	AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequest, Long id) throws Exception;

	void delete(Long id);

	AccommodationRequest toAccommodationRequest(AccommodationRequestDTO accommodationRequestDTO);

	AccommodationRequestDTO toAccommodationRequestDTO(AccommodationRequest accommodationRequest);

	List<AccommodationRequestDTO> toAccommodationRequestDtos(List<AccommodationRequest> accommodationRequests);

	void updateAccommodationRequest(AccommodationRequest target, AccommodationRequest source);
	
}
