package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;
import com.bookingapplication.bookingapp.domain.OwnerReview;
import com.bookingapplication.bookingapp.dtos.OwnerReviewDTO;

public interface OwnerReviewService {

	Collection<OwnerReviewDTO> findAll();
	Collection<OwnerReviewDTO> findAll(String ownerEmail);
	Collection<OwnerReviewDTO> findAll(boolean isReported);
	Collection<OwnerReviewDTO> findAll(String ownerEmail, boolean isReported);
	OwnerReviewDTO findOne(Long id);
	OwnerReviewDTO create(OwnerReviewDTO ownerReview) throws Exception;
	OwnerReviewDTO update(OwnerReviewDTO ownerReview, Long id) throws Exception;
	void delete(Long id);
	
	OwnerReview toOwnerReview(OwnerReviewDTO ownerReviewDTO);

	OwnerReviewDTO toOwnerReviewDTO(OwnerReview ownerReview);

    List<OwnerReviewDTO> toOwnerReviewDtos(List<OwnerReview> ownerReviews);

    void updateOwnerReview(OwnerReview target, OwnerReview source);

}
