package com.bookingapplication.bookingapp.mappers;

import java.util.List;

import org.mapstruct.MappingTarget;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;

public interface AccommodationMapper {
	
	Accommodation toAccommodation(AccommodationDTO accommodationDTO);

	AccommodationDTO toAccommodationDTO(Accommodation accommodation);

    List<AccommodationDTO> toAccommodationDtos(List<Accommodation> accommodations);

    void updateAccommodation(@MappingTarget Accommodation target, Accommodation source);

}

