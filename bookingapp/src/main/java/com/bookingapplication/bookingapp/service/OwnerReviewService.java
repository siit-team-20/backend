package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.OwnerReview;

public interface OwnerReviewService {
<<<<<<< HEAD
	
=======
>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
	Collection<OwnerReview> findAll();

	OwnerReview findOne(Long id);

	OwnerReview create(OwnerReview ownerReview) throws Exception;

	OwnerReview update(OwnerReview ownerReview) throws Exception;

	void delete(Long id);
<<<<<<< HEAD

=======
>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
}
