package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;

public interface FavouriteAccommodationService {
	Collection<FavouriteAccommodation> findAll();

	FavouriteAccommodation findOne(Long id);

	FavouriteAccommodation create(FavouriteAccommodation favouriteAccommodation) throws Exception;

	FavouriteAccommodation update(FavouriteAccommodation greeting) throws Exception;

	void delete(Long id);

}
