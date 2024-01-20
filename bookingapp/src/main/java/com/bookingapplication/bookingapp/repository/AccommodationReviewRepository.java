package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReview;
import com.bookingapplication.bookingapp.dtos.AccommodationReviewDTO;

public interface AccommodationReviewRepository {
	
	Collection<AccommodationReviewDTO> findAll();
	AccommodationReviewDTO findOne(Long id);
	AccommodationReviewDTO create(AccommodationReviewDTO accommodationReview);
	AccommodationReviewDTO update(AccommodationReviewDTO accommodationReview);
	void delete(Long id);

}
