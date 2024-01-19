package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.aot.AotServices.Source;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationReservationRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationReservationService;

@Service
public class AccommodationReservationServiceImpl implements AccommodationReservationService {

	@Autowired
	private AccommodationReservationRepositoryJpa accommodationReservationRepositoryJpa;
	
	public AccommodationReservationServiceImpl() {
	}
	
	@Override
	public Collection<AccommodationReservationDTO> findAll() {
		return toAccommodationReservationDtos(accommodationReservationRepositoryJpa.findAll());
	}

	@Override
	public Collection<AccommodationReservationDTO> findAll(String guestEmail) {
		return toAccommodationReservationDtos(accommodationReservationRepositoryJpa.findAll().stream().filter(a -> a.getGuestEmail().equals(guestEmail)).collect(Collectors.toList()));
	}

	@Override
	public AccommodationReservationDTO findOne(Long id) {
		AccommodationReservation accommodationReservation = accommodationReservationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Reservation not found", HttpStatus.NOT_FOUND));
        return toAccommodationReservationDTO(accommodationReservation);
	}

	@Override
	public AccommodationReservationDTO create(AccommodationReservationDTO accommodationReservationDTO) throws Exception {
		AccommodationReservation accommodationReservation = toAccommodationReservation(accommodationReservationDTO);
		if (accommodationReservation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationReservation savedAccommodationreReservation = accommodationReservationRepositoryJpa.save(accommodationReservation);
		return toAccommodationReservationDTO(savedAccommodationreReservation);
	}

	@Override
	public AccommodationReservationDTO update(AccommodationReservationDTO accommodationReservationDTO, Long id) throws Exception {
		AccommodationReservation accommodationReservation = accommodationReservationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Accommodation not found", HttpStatus.NOT_FOUND));

		updateAccommodationReservation(accommodationReservation, toAccommodationReservation(accommodationReservationDTO));

        AccommodationReservation savedAccommodationreReservation = accommodationReservationRepositoryJpa.save(accommodationReservation);

        return toAccommodationReservationDTO(savedAccommodationreReservation);
	}

	@Override
	public void delete(Long id) {
		accommodationReservationRepositoryJpa.deleteById(id);
	}

	@Override
	public AccommodationReservation toAccommodationReservation(
			AccommodationReservationDTO accommodationReservationDTO) {
		if ( accommodationReservationDTO == null ) {
            return null;
        }

        AccommodationReservation accommodationReservation = new AccommodationReservation();

        accommodationReservation.setId( accommodationReservationDTO.getId() );
        accommodationReservation.setGuestEmail( accommodationReservationDTO.getGuestEmail() );
        accommodationReservation.setAccommodationId( accommodationReservationDTO.getAccommodationId() );
        accommodationReservation.setDate( accommodationReservationDTO.getDate() );
        accommodationReservation.setDays( accommodationReservationDTO.getDays() );
        accommodationReservation.setGuestNumber( accommodationReservationDTO.getGuestNumber() );
        accommodationReservation.setPrice( accommodationReservationDTO.getPrice() );
        accommodationReservation.setStatus(accommodationReservationDTO.getStatus());
        
        return accommodationReservation;
	}

	@Override
	public AccommodationReservationDTO toAccommodationReservationDTO(
			AccommodationReservation accommodationReservation) {
		if ( accommodationReservation == null ) {
            return null;
        }

        AccommodationReservationDTO accommodationReservationDTO = new AccommodationReservationDTO();

        accommodationReservationDTO.setId( accommodationReservation.getId() );
        accommodationReservationDTO.setGuestEmail( accommodationReservation.getGuestEmail() );
        accommodationReservationDTO.setAccommodationId( accommodationReservation.getAccommodationId() );
        accommodationReservationDTO.setDate( accommodationReservation.getDate() );
        accommodationReservationDTO.setDays( accommodationReservation.getDays() );
        accommodationReservationDTO.setGuestNumber( accommodationReservation.getGuestNumber() );
        accommodationReservationDTO.setPrice( accommodationReservation.getPrice() );
        accommodationReservationDTO.setStatus(accommodationReservation.getStatus());
        
        return accommodationReservationDTO;
	}

	@Override
	public List<AccommodationReservationDTO> toAccommodationReservationDtos(
			List<AccommodationReservation> accommodationReservations) {
		if ( accommodationReservations == null ) {
            return null;
        }

        List<AccommodationReservationDTO> list = new ArrayList<AccommodationReservationDTO>( accommodationReservations.size() );
        for ( AccommodationReservation accommodationReservation1 : accommodationReservations ) {
            list.add( toAccommodationReservationDTO( accommodationReservation1 ) );
        }

        return list;
	}

	@Override
	public void updateAccommodationReservation(AccommodationReservation target, AccommodationReservation source) {
		if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setGuestEmail( source.getGuestEmail() );
        target.setAccommodationId( source.getAccommodationId() );
        target.setDate( source.getDate() );
        target.setDays( source.getDays() );
        target.setGuestNumber( source.getGuestNumber() );
        target.setPrice( source.getPrice() );
        target.setStatus( source.getStatus());
		
	}

}
