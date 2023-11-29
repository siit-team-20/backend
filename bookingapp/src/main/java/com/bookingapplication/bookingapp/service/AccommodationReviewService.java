package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.AccommodationReview;

public interface AccommodationReviewService {
	
	Collection<AccommodationReview> findAll();
	AccommodationReview findOne(Long id);
	AccommodationReview create(AccommodationReview accommodationReview) throws Exception;
	AccommodationReview update(AccommodationReview accommodationReview) throws Exception;
	void delete(Long id);

}
