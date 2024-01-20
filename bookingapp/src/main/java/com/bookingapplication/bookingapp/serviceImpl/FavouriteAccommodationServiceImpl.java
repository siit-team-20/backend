package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;
import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryImpl.InMemoryFavouriteAccommodationRepository;
import com.bookingapplication.bookingapp.repositoryjpa.FavouriteAccommodationRepositoryJpa;
import com.bookingapplication.bookingapp.service.FavouriteAccommodationService;

@Service
public class FavouriteAccommodationServiceImpl implements FavouriteAccommodationService {

	@Autowired
	private FavouriteAccommodationRepositoryJpa favouriteAccommodationRepositoryJpa;

	@Override
	public Collection<FavouriteAccommodationDTO> findAll() {
		return toFavouriteAccommodationDTOs(favouriteAccommodationRepositoryJpa.findAll());
	}

	@Override
	public FavouriteAccommodationDTO findOne(Long id) {
		FavouriteAccommodation favouriteAccommodation = favouriteAccommodationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Favourite Accommodation not found", HttpStatus.NOT_FOUND));
        return toFavouriteAccommodationDTO(favouriteAccommodation);
	}

	@Override
	public FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodationDTO) throws Exception {
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
        favouriteAccommodation.setGuestid(favouriteAccommodationDTO.getGuestid());
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
        favouriteAccommodationDTO.setGuestid(favouriteAccommodation.getGuestid());
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
        target.setGuestid(source.getGuestid());
    }

}

