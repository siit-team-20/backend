package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Accommodation;

public interface AccommodationRepository {
	
	Collection<Accommodation> findAll();
	Accommodation findOne(Long id);
	Accommodation create(Accommodation accommodation);
	Accommodation update(Accommodation accommodation);
	void delete(Long id);

}
