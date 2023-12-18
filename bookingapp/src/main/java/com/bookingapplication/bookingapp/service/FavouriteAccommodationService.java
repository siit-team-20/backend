package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;

public interface FavouriteAccommodationService {
	Collection<FavouriteAccommodationDTO> findAll();

	FavouriteAccommodationDTO findOne(Long id);

	FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation) throws Exception;

	FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation) throws Exception;

	void delete(Long id);

}
