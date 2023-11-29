package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Accommodation;

public interface AccommodationService {
	
	Collection<Accommodation> findAll();
	Accommodation findOne(Long id);
	Accommodation create(Accommodation accommodation) throws Exception;
	Accommodation update(Accommodation accommodation) throws Exception;
	void delete(Long id);

}
