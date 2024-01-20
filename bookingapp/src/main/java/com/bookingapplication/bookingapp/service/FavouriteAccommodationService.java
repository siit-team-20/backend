package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationWithAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;

public interface FavouriteAccommodationService {
	
	Collection<FavouriteAccommodationWithAccommodationDTO> findAll();
	Collection<FavouriteAccommodationWithAccommodationDTO> findAll(String guestEmail);

	FavouriteAccommodationDTO findOne(Long id);

	FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation) throws Exception;

	FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation, Long id) throws Exception;

	void delete(Long id);
	
	FavouriteAccommodation toFavouriteAccommodation(FavouriteAccommodationDTO favouriteAccommodationDTO);

	FavouriteAccommodationDTO toFavouriteAccommodationDTO(FavouriteAccommodation favouriteAccommodation);

	List<FavouriteAccommodationDTO> toFavouriteAccommodationDTOs(List<FavouriteAccommodation> favouriteAccommodations);

	void updateFavouriteAccommodation(FavouriteAccommodation target, FavouriteAccommodation source);
	
	// ---------------------------------
    
	FavouriteAccommodation toFavouriteAccommodation(FavouriteAccommodationWithAccommodationDTO favouriteAccommodationWithAccommodationDTO);

	FavouriteAccommodationWithAccommodationDTO toFavouriteAccommodationWithAccommodationDTO(FavouriteAccommodation favouriteAccommodation);

    List<FavouriteAccommodationWithAccommodationDTO> toFavouriteAccommodationWithAccommodationDtos(List<FavouriteAccommodation> favouriteAccommodations);


}
