package com.bookingapplication.bookingapp.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.property.access.spi.BuiltInPropertyAccessStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.ReservationStatus;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationReservationRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationReservationService;
import com.bookingapplication.bookingapp.service.AccommodationService;

@Service
public class AccommodationReservationServiceImpl implements AccommodationReservationService {

	@Autowired
	private AccommodationReservationRepositoryJpa accommodationReservationRepositoryJpa;
	@Autowired
	private AccommodationService accommodationService;
	
	public AccommodationReservationServiceImpl() {
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll() {
		return toReservationWithAccommodationDtos(accommodationReservationRepositoryJpa.findAll());
	}

	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(String guestEmail) {
		return toReservationWithAccommodationDtos(accommodationReservationRepositoryJpa.findAll().stream().filter(a -> a.getGuestEmail().equals(guestEmail)).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(String ownerEmail, String guestEmail, ReservationStatus status) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		List<AccommodationReservation> ownersAccommodationReservations = new ArrayList<AccommodationReservation>();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			AccommodationDTO accommodation = accommodationService.findOne(accommodationReservation.getAccommodationId());
			if (accommodation.getOwnerEmail().equals(ownerEmail) && accommodationReservation.getStatus().equals(status) && accommodationReservation.getGuestEmail().equals(guestEmail)) {
				ownersAccommodationReservations.add(accommodationReservation);
			}
		}
		return toReservationWithAccommodationDtos(ownersAccommodationReservations);	
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(String guestEmail, ReservationStatus status, Long days, Long accommodationId) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		List<AccommodationReservation> ownersAccommodationReservations = new ArrayList<AccommodationReservation>();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			if (accommodationReservation.getStatus().equals(status) && accommodationReservation.getGuestEmail().equals(guestEmail) && accommodationReservation.getAccommodationId().equals(accommodationId)) {
				LocalDate endDate = accommodationReservation.getDate();
				endDate.plusDays(accommodationReservation.getDays());
				if (endDate.plusDays(days).isAfter(LocalDate.now()))
					ownersAccommodationReservations.add(accommodationReservation);
			}
		}
		return toReservationWithAccommodationDtos(ownersAccommodationReservations);	
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(String guestEmail, ReservationStatus status) {
		return toReservationWithAccommodationDtos(accommodationReservationRepositoryJpa.findAll().stream().filter(a -> a.getGuestEmail().equals(guestEmail) && a.getStatus().equals(status)).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(Long accommodationId, ReservationStatus status) {
		return toReservationWithAccommodationDtos(accommodationReservationRepositoryJpa.findAll().stream().filter(a -> a.getAccommodationId().equals(accommodationId) && a.getStatus().equals(status)).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAll(Long accommodationId) {
		return toReservationWithAccommodationDtos(accommodationReservationRepositoryJpa.findAll().stream().filter(a -> a.getAccommodationId().equals(accommodationId) && (a.getStatus().equals(ReservationStatus.Waiting) || a.getStatus().equals(ReservationStatus.Approved))).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAllByOwnerEmail(String ownerEmail) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		List<AccommodationReservation> ownersAccommodationReservations = new ArrayList<AccommodationReservation>();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			AccommodationDTO accommodation = accommodationService.findOne(accommodationReservation.getAccommodationId());
			if (accommodation.getOwnerEmail().equals(ownerEmail)) {
				ownersAccommodationReservations.add(accommodationReservation);
			}
		}
		return toReservationWithAccommodationDtos(ownersAccommodationReservations);
	}
	
	@Override
	public Collection<ReservationWithAccommodationDTO> findAllByOwnerEmailAndStatus(String ownerEmail, ReservationStatus status) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		List<AccommodationReservation> ownersAccommodationReservations = new ArrayList<AccommodationReservation>();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			AccommodationDTO accommodation = accommodationService.findOne(accommodationReservation.getAccommodationId());
			if (accommodation.getOwnerEmail().equals(ownerEmail) && accommodationReservation.getStatus().equals(status)) {
				ownersAccommodationReservations.add(accommodationReservation);
			}
		}
		return toReservationWithAccommodationDtos(ownersAccommodationReservations);
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
	public AccommodationReservationDTO update(ReservationWithAccommodationDTO accommodationReservationDTO, Long id) throws Exception {
		AccommodationReservation accommodationReservation = accommodationReservationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Accommodation reservation not found", HttpStatus.NOT_FOUND));

		updateAccommodationReservation(accommodationReservation, toAccommodationReservation(accommodationReservationDTO));

        AccommodationReservation savedAccommodationreReservation = accommodationReservationRepositoryJpa.save(accommodationReservation);

        return toAccommodationReservationDTO(savedAccommodationreReservation);
	}

	@Override
	public void delete(Long id) {
		accommodationReservationRepositoryJpa.deleteById(id);
	}
	
	@Override
	public void deleteAll(String guestEmail, ReservationStatus status) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			if (accommodationReservation.getGuestEmail().equals(guestEmail)) {
				accommodationReservationRepositoryJpa.deleteById(accommodationReservation.getId());
			}
		}
	}
	
	@Override
	public void deleteAllByOwnerEmail(String ownerEmail, ReservationStatus status) {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			AccommodationDTO accommodation = accommodationService.findOne(accommodationReservation.getAccommodationId());
			if (accommodation.getOwnerEmail().equals(ownerEmail)) {
				accommodationReservationRepositoryJpa.deleteById(accommodationReservation.getId());
			}
		}
	}
	
	public void updateStatuses() {
		List<AccommodationReservation> accommodationReservations = accommodationReservationRepositoryJpa.findAll();
		for (AccommodationReservation accommodationReservation : accommodationReservations) {
			LocalDate endDate = accommodationReservation.getDate();
			endDate = endDate.plusDays(accommodationReservation.getDays());
			if (accommodationReservation.getStatus() == ReservationStatus.Approved && endDate.isBefore(LocalDate.now())) {
				accommodationReservation.setStatus(ReservationStatus.Finished);
		        accommodationReservationRepositoryJpa.save(accommodationReservation);
			}
			if (accommodationReservation.getStatus() == ReservationStatus.Waiting && accommodationReservation.getDate().isBefore(LocalDate.now())) {
				accommodationReservation.setStatus(ReservationStatus.Rejected);
		        accommodationReservationRepositoryJpa.save(accommodationReservation);
			}
		}
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

	@Override
	public AccommodationReservation toAccommodationReservation(
			ReservationWithAccommodationDTO reservationWithAccommodationDTO) {
		if ( reservationWithAccommodationDTO == null ) {
            return null;
        }

        AccommodationReservation accommodationReservation = new AccommodationReservation();

        accommodationReservation.setId( reservationWithAccommodationDTO.getId() );
        accommodationReservation.setGuestEmail( reservationWithAccommodationDTO.getGuestEmail() );

        Long accommodationId = null;
        if (reservationWithAccommodationDTO.getAccommodation() != null)
        	accommodationId = reservationWithAccommodationDTO.getAccommodation().getId();
        accommodationReservation.setAccommodationId( accommodationId );
        
        accommodationReservation.setDate( reservationWithAccommodationDTO.getDate() );
        accommodationReservation.setDays( reservationWithAccommodationDTO.getDays() );
        accommodationReservation.setGuestNumber( reservationWithAccommodationDTO.getGuestNumber() );
        accommodationReservation.setPrice( reservationWithAccommodationDTO.getPrice() );
        accommodationReservation.setStatus(reservationWithAccommodationDTO.getStatus());
        
        return accommodationReservation;
	}

	@Override
	public ReservationWithAccommodationDTO toReservationWithAccommodationDTO(
			AccommodationReservation accommodationReservation) {
		if ( accommodationReservation == null ) {
            return null;
        }

		ReservationWithAccommodationDTO reservationWithAccommodationDTO = new ReservationWithAccommodationDTO();

		reservationWithAccommodationDTO.setId( accommodationReservation.getId() );
		reservationWithAccommodationDTO.setGuestEmail( accommodationReservation.getGuestEmail() );

        AccommodationDTO accommodation = null;
        if (accommodationReservation.getAccommodationId() != null)
        	accommodation = accommodationService.findOne(accommodationReservation.getAccommodationId());
        
        reservationWithAccommodationDTO.setAccommodation( accommodation );
        reservationWithAccommodationDTO.setDate( accommodationReservation.getDate() );
        reservationWithAccommodationDTO.setDays( accommodationReservation.getDays() );
        reservationWithAccommodationDTO.setGuestNumber( accommodationReservation.getGuestNumber() );
        reservationWithAccommodationDTO.setPrice( accommodationReservation.getPrice() );
        reservationWithAccommodationDTO.setStatus(accommodationReservation.getStatus());
        
        return reservationWithAccommodationDTO;
	}

	@Override
	public List<ReservationWithAccommodationDTO> toReservationWithAccommodationDtos(
			List<AccommodationReservation> accommodationReservations) {
		if ( accommodationReservations == null ) {
            return null;
        }

        List<ReservationWithAccommodationDTO> list = new ArrayList<ReservationWithAccommodationDTO>( accommodationReservations.size() );
        for ( AccommodationReservation accommodationReservation1 : accommodationReservations ) {
            list.add( toReservationWithAccommodationDTO( accommodationReservation1 ) );
        }

        return list;
	}

}
