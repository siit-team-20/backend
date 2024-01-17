package com.bookingapplication.bookingapp.mappers;

import java.util.List;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;

@Service
public interface AccommodationRequestMapper {
	
	AccommodationRequest toAccommodationRequest(AccommodationRequestDTO accommodationRequestDTO);

	AccommodationRequestDTO toAccommodationRequestDTO(AccommodationRequest accommodationRequest);

    List<AccommodationRequestDTO> toAccommodationRequestDtos(List<AccommodationRequest> accommodationRequests);

    void updateAccommodationRequest(@MappingTarget AccommodationRequest target, AccommodationRequest source);

}

