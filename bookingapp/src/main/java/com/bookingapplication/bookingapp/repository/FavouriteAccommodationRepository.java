package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;

public interface FavouriteAccommodationRepository {
	Collection<FavouriteAccommodationDTO> findAll();

	FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation);

	FavouriteAccommodationDTO findOne(Long id);
	
	FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation);

	void delete(Long id);
}
