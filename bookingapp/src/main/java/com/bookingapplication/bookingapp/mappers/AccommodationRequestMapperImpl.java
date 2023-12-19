package com.bookingapplication.bookingapp.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;
import com.bookingapplication.bookingapp.service.AccommodationService;

@Service
public class AccommodationRequestMapperImpl implements AccommodationRequestMapper {
	
	@Autowired
	private AccommodationService accommodationService;

    @Override
    public AccommodationRequest toAccommodationRequest(AccommodationRequestDTO accommodationRequestDTO) {
        if ( accommodationRequestDTO == null ) {
            return null;
        }

        AccommodationRequest accommodationRequest = new AccommodationRequest();

        accommodationRequest.setId( accommodationRequestDTO.id );
        if (accommodationRequestDTO.oldAccommodation != null)
        	accommodationRequest.setOldAccommodationId(accommodationRequestDTO.oldAccommodation.getId());
        accommodationRequest.setNewAccommodationId(accommodationRequestDTO.newAccommodation.getId());
        accommodationRequest.setAccommodationRequestType(accommodationRequestDTO.type);

        return accommodationRequest;
    }

    @Override
    public AccommodationRequestDTO toAccommodationRequestDTO(AccommodationRequest accommodationRequest) {
        if ( accommodationRequest == null ) {
            return null;
        }

        Long id = null;

        id = accommodationRequest.getId();

        AccommodationDTO oldAccommodation = null;
        if (accommodationRequest.getOldAccommodationId() != null)
        	oldAccommodation = accommodationService.findOne(accommodationRequest.getOldAccommodationId());
        AccommodationDTO newAccommodation = accommodationService.findOne(accommodationRequest.getNewAccommodationId());
        AccommodationRequestType type = accommodationRequest.getAccommodationRequestType();

        AccommodationRequestDTO accommodationRequestDTO = new AccommodationRequestDTO( id, oldAccommodation, newAccommodation, type );

        return accommodationRequestDTO;
    }

    @Override
    public List<AccommodationRequestDTO> toAccommodationRequestDtos(List<AccommodationRequest> accommodationRequests) {
        if ( accommodationRequests == null ) {
            return null;
        }

        List<AccommodationRequestDTO> list = new ArrayList<AccommodationRequestDTO>( accommodationRequests.size() );
        for ( AccommodationRequest accommodationRequest : accommodationRequests ) {
            list.add( toAccommodationRequestDTO( accommodationRequest ) );
        }

        return list;
    }

    @Override
    public void updateAccommodationRequest(AccommodationRequest target, AccommodationRequest source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setOldAccommodationId( source.getOldAccommodationId() );
        target.setNewAccommodationId( source.getNewAccommodationId() );
        target.setAccommodationRequestType( source.getAccommodationRequestType());
    }
}
