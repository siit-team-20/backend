package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.OwnerReview;

public interface OwnerReviewService {
	
	Collection<OwnerReview> findAll();

	OwnerReview findOne(Long id);

	OwnerReview create(OwnerReview ownerReview) throws Exception;

	OwnerReview update(OwnerReview ownerReview) throws Exception;

	void delete(Long id);

}
