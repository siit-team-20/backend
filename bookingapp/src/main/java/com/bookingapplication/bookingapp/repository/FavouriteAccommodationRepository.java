package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;

public interface FavouriteAccommodationRepository {
	Collection<FavouriteAccommodation> findAll();

	FavouriteAccommodation create(FavouriteAccommodation favouriteAccommodation);

	FavouriteAccommodation findOne(Long id);
	
	FavouriteAccommodation update(FavouriteAccommodation favouriteAccommodation);

	void delete(Long id);
}
