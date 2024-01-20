package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;

public interface FavouriteAccommodationService {
	Collection<FavouriteAccommodationDTO> findAll();

	FavouriteAccommodationDTO findOne(Long id);

	FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation) throws Exception;

	FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation, Long id) throws Exception;

	void delete(Long id);
	
	FavouriteAccommodation toFavouriteAccommodation(FavouriteAccommodationDTO favouriteAccommodationDTO);

	FavouriteAccommodationDTO toFavouriteAccommodationDTO(FavouriteAccommodation favouriteAccommodation);

	List<FavouriteAccommodationDTO> toFavouriteAccommodationDTOs(List<FavouriteAccommodation> favouriteAccommodations);

	void updateFavouriteAccommodation(FavouriteAccommodation target, FavouriteAccommodation source);

}
