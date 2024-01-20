package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.OwnerReview;
import com.bookingapplication.bookingapp.dtos.OwnerReviewDTO;

public interface OwnerReviewRepository {
	Collection<OwnerReviewDTO> findAll();

	OwnerReviewDTO create(OwnerReviewDTO ownerReview);

	OwnerReviewDTO findOne(Long id);
	
	OwnerReviewDTO update(OwnerReviewDTO ownerReview);

	void delete(Long id);
}
