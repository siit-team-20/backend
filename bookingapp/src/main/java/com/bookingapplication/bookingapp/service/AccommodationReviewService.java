package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;
import com.bookingapplication.bookingapp.domain.AccommodationReview;
import com.bookingapplication.bookingapp.dtos.AccommodationReviewDTO;

public interface AccommodationReviewService {
	
	Collection<AccommodationReviewDTO> findAll();
	Collection<AccommodationReviewDTO> findAll(String guestEmail);
	Collection<AccommodationReviewDTO> findAll(Long accommodationId);
	AccommodationReviewDTO findOne(Long id);
	AccommodationReviewDTO create(AccommodationReviewDTO accommodationReview) throws Exception;
	AccommodationReviewDTO update(AccommodationReviewDTO accommodationReview, Long id) throws Exception;
	void delete(Long id);
	
	AccommodationReview toAccommodationReview(AccommodationReviewDTO accommodationReviewDTO);

	AccommodationReviewDTO toAccommodationReviewDTO(AccommodationReview accommodationReview);

    List<AccommodationReviewDTO> toAccommodationReviewDtos(List<AccommodationReview> accommodationReviews);

    void updateAccommodationReview(AccommodationReview target, AccommodationReview source);

}
