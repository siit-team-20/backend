package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationWithAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryImpl.InMemoryFavouriteAccommodationRepository;
import com.bookingapplication.bookingapp.repositoryjpa.FavouriteAccommodationRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationService;
import com.bookingapplication.bookingapp.service.FavouriteAccommodationService;

@Service
public class FavouriteAccommodationServiceImpl implements FavouriteAccommodationService {

	@Autowired
	private FavouriteAccommodationRepositoryJpa favouriteAccommodationRepositoryJpa;
	@Autowired
	private AccommodationService accommodationService;

	@Override
	public Collection<FavouriteAccommodationWithAccommodationDTO> findAll() {
		return toFavouriteAccommodationWithAccommodationDtos(favouriteAccommodationRepositoryJpa.findAll());
	}

	@Override
	public Collection<FavouriteAccommodationWithAccommodationDTO> findAll(String guestEmail) {
		return toFavouriteAccommodationWithAccommodationDtos(favouriteAccommodationRepositoryJpa.findAll().stream().filter(a -> a.getGuestEmail().equals(guestEmail)).collect(Collectors.toList()));
	}

	@Override
	public FavouriteAccommodationDTO findOne(Long id) {
		FavouriteAccommodation favouriteAccommodation = favouriteAccommodationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Favourite Accommodation not found", HttpStatus.NOT_FOUND));
        return toFavouriteAccommodationDTO(favouriteAccommodation);
	}

	@Override
	public FavouriteAccommodationDTO create(FavouriteAccommodationWithAccommodationDTO favouriteAccommodationDTO) throws Exception {
		FavouriteAccommodation favouriteAccommodation = toFavouriteAccommodation(favouriteAccommodationDTO);
		if (favouriteAccommodation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		FavouriteAccommodation savedFavouriteAccommodation = favouriteAccommodationRepositoryJpa.save(favouriteAccommodation);
		return toFavouriteAccommodationDTO(savedFavouriteAccommodation);
	}

	@Override
	public FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodationDTO, Long id) throws Exception {

		FavouriteAccommodation favouriteAccommodation = favouriteAccommodationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Favourite Accommodation not found", HttpStatus.NOT_FOUND));

		updateFavouriteAccommodation(favouriteAccommodation, toFavouriteAccommodation(favouriteAccommodationDTO));

        FavouriteAccommodation savedFavouriteAccommodation = favouriteAccommodationRepositoryJpa.save(favouriteAccommodation);

        return toFavouriteAccommodationDTO(savedFavouriteAccommodation);
	}

	@Override
	public void delete(Long id) {
		favouriteAccommodationRepositoryJpa.deleteById(id);
	}
	
	@Override
    public FavouriteAccommodation toFavouriteAccommodation(FavouriteAccommodationDTO favouriteAccommodationDTO) {
		if ( favouriteAccommodationDTO == null ) {
            return null;
        }

        FavouriteAccommodation favouriteAccommodation = new FavouriteAccommodation();

        favouriteAccommodation.setId( favouriteAccommodationDTO.getId() );
        favouriteAccommodation.setGuestEmail(favouriteAccommodationDTO.getGuestEmail());
        favouriteAccommodation.setAccommodationId(favouriteAccommodationDTO.getAccommodationId());
        
        
        return favouriteAccommodation;
    }

    @Override
    public FavouriteAccommodationDTO toFavouriteAccommodationDTO(FavouriteAccommodation favouriteAccommodation) {
    	if ( favouriteAccommodation == null ) {
            return null;
        }

        FavouriteAccommodationDTO favouriteAccommodationDTO = new FavouriteAccommodationDTO();

        favouriteAccommodationDTO.setId( favouriteAccommodation.getId() );
        favouriteAccommodationDTO.setGuestEmail(favouriteAccommodation.getGuestEmail());
        favouriteAccommodationDTO.setAccommodationId(favouriteAccommodation.getAccommodationId());

        return favouriteAccommodationDTO;
    }

    @Override
    public List<FavouriteAccommodationDTO> toFavouriteAccommodationDTOs(List<FavouriteAccommodation> favouriteAccommodations) {
        if ( favouriteAccommodations == null ) {
            return null;
        }

        List<FavouriteAccommodationDTO> list = new ArrayList<FavouriteAccommodationDTO>( favouriteAccommodations.size() );
        for ( FavouriteAccommodation favouriteAccommodation : favouriteAccommodations ) {
            list.add( toFavouriteAccommodationDTO( favouriteAccommodation ) );
        }

        return list;
    }

    @Override
    public void updateFavouriteAccommodation(FavouriteAccommodation target, FavouriteAccommodation source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setAccommodationId( source.getAccommodationId() );
        target.setGuestEmail(source.getGuestEmail());
    }
    
    // ----------------------

	@Override
	public FavouriteAccommodation toFavouriteAccommodation(
			FavouriteAccommodationWithAccommodationDTO favouriteAccommodationWithAccommodationDTO) {
		if ( favouriteAccommodationWithAccommodationDTO == null ) {
            return null;
        }

        FavouriteAccommodation favouriteAccommodation = new FavouriteAccommodation();

        favouriteAccommodation.setId( favouriteAccommodationWithAccommodationDTO.getId() );
        favouriteAccommodation.setGuestEmail( favouriteAccommodationWithAccommodationDTO.getGuestEmail() );

        Long accommodationId = null;
        if (favouriteAccommodationWithAccommodationDTO.getAccommodation() != null)
        	accommodationId = favouriteAccommodationWithAccommodationDTO.getAccommodation().getId();
        favouriteAccommodation.setAccommodationId( accommodationId );
        
        return favouriteAccommodation;
	}

	@Override
	public FavouriteAccommodationWithAccommodationDTO toFavouriteAccommodationWithAccommodationDTO(
			FavouriteAccommodation favouriteAccommodation) {
		if ( favouriteAccommodation == null ) {
            return null;
        }

		FavouriteAccommodationWithAccommodationDTO favouriteAccommodationWithAccommodationDTO = new FavouriteAccommodationWithAccommodationDTO();

		favouriteAccommodationWithAccommodationDTO.setId( favouriteAccommodation.getId() );
		favouriteAccommodationWithAccommodationDTO.setGuestEmail( favouriteAccommodation.getGuestEmail() );

        AccommodationDTO accommodation = null;
        if (favouriteAccommodation.getAccommodationId() != null)
        	accommodation = accommodationService.findOne(favouriteAccommodation.getAccommodationId());
        favouriteAccommodationWithAccommodationDTO.setAccommodation( accommodation );
        
        return favouriteAccommodationWithAccommodationDTO;
	}

	@Override
	public List<FavouriteAccommodationWithAccommodationDTO> toFavouriteAccommodationWithAccommodationDtos(
			List<FavouriteAccommodation> favouriteAccommodations) {
		if ( favouriteAccommodations == null ) {
            return null;
        }

        List<FavouriteAccommodationWithAccommodationDTO> list = new ArrayList<FavouriteAccommodationWithAccommodationDTO>( favouriteAccommodations.size() );
        for ( FavouriteAccommodation favouriteAccommodation1 : favouriteAccommodations ) {
            list.add( toFavouriteAccommodationWithAccommodationDTO( favouriteAccommodation1 ) );
        }

        return list;
	}

}

