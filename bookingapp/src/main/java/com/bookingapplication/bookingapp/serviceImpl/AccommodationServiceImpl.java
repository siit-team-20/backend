package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.DateRangeDTO;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationService;
import com.bookingapplication.bookingapp.service.DateRangeService;
import com.bookingapplication.bookingapp.exceptions.AppException;

@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepositoryJpa accommodationRepositoryJpa;
	@Autowired
	private DateRangeService dateRangeService;

	
	public AccommodationServiceImpl() {
	}

	@Override
	public Collection<AccommodationDTO> findAll() {
		return toAccommodationDtos(accommodationRepositoryJpa.findAll());
	}
	
	@Override
	public Collection<AccommodationDTO> findAll(String ownerEmail) {
		return toAccommodationDtos(accommodationRepositoryJpa.findAll().stream().filter(a -> a.getOwnerEmail().equals(ownerEmail)).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<AccommodationDTO> findAll(boolean onlyApproved) {
		return toAccommodationDtos(accommodationRepositoryJpa.findAll().stream().filter(a -> a.getIsApproved() == onlyApproved).collect(Collectors.toList()));
	}

	@Override
	public AccommodationDTO findOne(Long id) {
		Accommodation accommodation = accommodationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Accommodation not found", HttpStatus.NOT_FOUND));
        return toAccommodationDTO(accommodation);
	}

	@Override
	public AccommodationDTO create(AccommodationDTO accommodationDTO) throws Exception {
		Accommodation accommodation = toAccommodation(accommodationDTO);
		if (accommodation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Accommodation savedAccommodation = accommodationRepositoryJpa.save(accommodation);
		return toAccommodationDTO(savedAccommodation);
	}
	
	@Override
	public AccommodationDTO update(AccommodationDTO accommodationDTO, Long id) throws Exception {
		Accommodation accommodation = accommodationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Accommodation not found", HttpStatus.NOT_FOUND));

		updateAccommodation(accommodation, toAccommodation(accommodationDTO));

        Accommodation savedAccommodation = accommodationRepositoryJpa.save(accommodation);

        return toAccommodationDTO(savedAccommodation);
	}

	@Override
	public void delete(Long id) {
		accommodationRepositoryJpa.deleteById(id);
	}
	
	@Override
    public Accommodation toAccommodation(AccommodationDTO accommodationDTO) {
        if ( accommodationDTO == null ) {
            return null;
        }

        Accommodation accommodation = new Accommodation();

        accommodation.setId( accommodationDTO.getId() );
        accommodation.setOwnerEmail( accommodationDTO.getOwnerEmail() );
        accommodation.setName( accommodationDTO.getName() );
        accommodation.setDescription( accommodationDTO.getDescription() );
        accommodation.setLocation( accommodationDTO.getLocation() );
        accommodation.setMinGuests( accommodationDTO.getMinGuests() );
        accommodation.setMaxGuests( accommodationDTO.getMaxGuests() );
        accommodation.setAccommodationType( accommodationDTO.getAccommodationType() );
        List<String> list = accommodationDTO.getBenefits();
        if ( list != null ) {
            accommodation.setBenefits( new ArrayList<String>( list ) );
        }
        accommodation.setIsApproved( accommodationDTO.getIsApproved() );
        accommodation.setIsPriceByGuest( accommodationDTO.getIsPriceByGuest() );
        accommodation.setReservationCancellationDeadline( accommodationDTO.getReservationCancellationDeadline() );
        accommodation.setIsAutomaticAcceptance(accommodationDTO.getIsAutomaticAcceptance());
        
        return accommodation;
    }

    @Override
    public AccommodationDTO toAccommodationDTO(Accommodation accommodation) {
        if ( accommodation == null ) {
            return null;
        }

        AccommodationDTO accommodationDTO = new AccommodationDTO();

        accommodationDTO.setId( accommodation.getId() );
        accommodationDTO.setOwnerEmail( accommodation.getOwnerEmail() );
        accommodationDTO.setName( accommodation.getName() );
        accommodationDTO.setDescription( accommodation.getDescription() );
        accommodationDTO.setLocation( accommodation.getLocation() );
        accommodationDTO.setMinGuests( accommodation.getMinGuests() );
        accommodationDTO.setMaxGuests( accommodation.getMaxGuests() );
        accommodationDTO.setAccommodationType( accommodation.getAccommodationType() );
        List<String> list = accommodation.getBenefits();
        if ( list != null ) {
            accommodationDTO.setBenefits( new ArrayList<String>( list ) );
        }
        Collection<DateRangeDTO> list1 = dateRangeService.findAll(accommodation.getId());
        if ( list1 != null ) {
            accommodationDTO.setAvailabilityDates( new ArrayList<DateRangeDTO>( list1 ) );
        }
        accommodationDTO.setIsApproved( accommodation.getIsApproved() );
        accommodationDTO.setIsPriceByGuest( accommodation.getIsPriceByGuest() );
        accommodationDTO.setReservationCancellationDeadline( accommodation.getReservationCancellationDeadline() );
        accommodationDTO.setIsAutomaticAcceptance( accommodation.getIsAutomaticAcceptance() );
        
        return accommodationDTO;
    }

    @Override
    public List<AccommodationDTO> toAccommodationDtos(List<Accommodation> accommodations) {
        if ( accommodations == null ) {
            return null;
        }

        List<AccommodationDTO> list = new ArrayList<AccommodationDTO>( accommodations.size() );
        for ( Accommodation accommodation1 : accommodations ) {
            list.add( toAccommodationDTO( accommodation1 ) );
        }

        return list;
    }

    @Override
    public void updateAccommodation(Accommodation target, Accommodation source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setOwnerEmail( source.getOwnerEmail() );
        target.setName( source.getName() );
        target.setDescription( source.getDescription() );
        target.setLocation( source.getLocation() );
        target.setMinGuests( source.getMinGuests() );
        target.setMaxGuests( source.getMaxGuests() );
        target.setAccommodationType( source.getAccommodationType() );
        if ( target.getBenefits() != null ) {
            List<String> list = source.getBenefits();
            if ( list != null ) {
                target.getBenefits().clear();
                target.getBenefits().addAll( list );
            }
            else {
                target.setBenefits( null );
            }
        }
        else {
            List<String> list = source.getBenefits();
            if ( list != null ) {
                target.setBenefits( new ArrayList<String>( list ) );
            }
        }
        
        target.setIsApproved( source.getIsApproved() );
        target.setIsPriceByGuest( source.getIsPriceByGuest() );
        target.setReservationCancellationDeadline( source.getReservationCancellationDeadline() );
        target.setIsAutomaticAcceptance(source.getIsAutomaticAcceptance());
    }

}
