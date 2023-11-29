package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.OwnerReview;

public interface OwnerReviewRepository {
	Collection<OwnerReview> findAll();

	OwnerReview create(OwnerReview ownerReview);

	OwnerReview findOne(Long id);
	
	OwnerReview update(OwnerReview ownerReview);

	void delete(Long id);
}
