package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReview;

public interface AccommodationReviewRepository {
	
	Collection<AccommodationReview> findAll();
	AccommodationReview findOne(Long id);
	AccommodationReview create(AccommodationReview accommodationReview);
	AccommodationReview update(AccommodationReview accommodationReview);
	void delete(Long id);

}
