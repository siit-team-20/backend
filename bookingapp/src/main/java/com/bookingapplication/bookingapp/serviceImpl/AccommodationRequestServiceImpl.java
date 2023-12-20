package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationRequestRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationRequestService;
import com.bookingapplication.bookingapp.service.AccommodationService;


@Service
public class AccommodationRequestServiceImpl implements AccommodationRequestService {

	@Autowired
	private AccommodationRequestRepositoryJpa accommodationRequestRepositoryJpa;
	@Autowired
	private AccommodationService accommodationService;

	@Override
	public Collection<AccommodationRequestDTO> findAll() {
		return toAccommodationRequestDtos(accommodationRequestRepositoryJpa.findAll());
	}

	@Override
	public AccommodationRequestDTO findOne(Long id) {
		AccommodationRequest accommodationRequest = accommodationRequestRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Accommodation request not found", HttpStatus.NOT_FOUND));
        return toAccommodationRequestDTO(accommodationRequest);
	}

	@Override
	public AccommodationRequestDTO create(AccommodationDTO accommodation, AccommodationRequestType accommodationRequestType) throws Exception {
		AccommodationRequestDTO accommodationRequestDTO = new AccommodationRequestDTO(null, null, accommodation, accommodationRequestType);
		AccommodationRequest accommodationRequest = toAccommodationRequest(accommodationRequestDTO);
		if (accommodationRequest.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationRequest savedAccommodationRequest = accommodationRequestRepositoryJpa.save(accommodationRequest);
		return toAccommodationRequestDTO(savedAccommodationRequest);
	}
	
	@Override
	public AccommodationRequestDTO create(AccommodationDTO oldAccommodationDTO, AccommodationDTO newAccommodationDTO, AccommodationRequestType accommodationRequestType) throws Exception {
		AccommodationRequestDTO accommodationRequestDTO = new AccommodationRequestDTO(null, oldAccommodationDTO, newAccommodationDTO, accommodationRequestType);
		AccommodationRequest accommodationRequest = toAccommodationRequest(accommodationRequestDTO);
		if (accommodationRequest.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationRequest savedAccommodationRequest = accommodationRequestRepositoryJpa.save(accommodationRequest);
		return toAccommodationRequestDTO(savedAccommodationRequest);
	}
	
	@Override
	public AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequestDTO) throws Exception {
		AccommodationRequest accommodationRequest = toAccommodationRequest(accommodationRequestDTO);
		if (accommodationRequest.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationRequest savedAccommodationRequest = accommodationRequestRepositoryJpa.save(accommodationRequest);
		return toAccommodationRequestDTO(savedAccommodationRequest);
	}

	@Override
	public AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequestDTO, Long id) throws Exception {
		AccommodationRequest accommodationRequest = accommodationRequestRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Accommodation request not found", HttpStatus.NOT_FOUND));

		updateAccommodationRequest(accommodationRequest, toAccommodationRequest(accommodationRequestDTO));

        AccommodationRequest savedAccommodationRequest = accommodationRequestRepositoryJpa.save(accommodationRequest);

        return toAccommodationRequestDTO(savedAccommodationRequest);
	}

	@Override
	public void delete(Long id) {
		accommodationRequestRepositoryJpa.deleteById(id);
	}
	
	@Override
    public AccommodationRequest toAccommodationRequest(AccommodationRequestDTO accommodationRequestDTO) {
        if ( accommodationRequestDTO == null ) {
            return null;
        }

        AccommodationRequest accommodationRequest = new AccommodationRequest();

        accommodationRequest.setId( accommodationRequestDTO.id );
        if (accommodationRequestDTO.oldAccommodation != null)
        	accommodationRequest.setOldAccommodationId(accommodationRequestDTO.oldAccommodation.getId());
        if (accommodationRequestDTO.newAccommodation != null)
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
        AccommodationDTO newAccommodation = null;
        if (accommodationRequest.getOldAccommodationId() != null)
        	oldAccommodation = accommodationService.findOne(accommodationRequest.getOldAccommodationId());
        if (accommodationRequest.getNewAccommodationId() != null)
        	newAccommodation = accommodationService.findOne(accommodationRequest.getNewAccommodationId());
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
