package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.bookingapplication.bookingapp.dtos.OwnerReviewDTO;
import com.bookingapplication.bookingapp.repository.OwnerReviewRepository;

@Repository
public class InMemoryOwnerReviewRepository implements OwnerReviewRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, OwnerReviewDTO> ownerReviews = new ConcurrentHashMap<Long, OwnerReviewDTO>();

	@Override
	public Collection<OwnerReviewDTO> findAll() {
		return this.ownerReviews.values();
	}

	@Override
	public OwnerReviewDTO create(OwnerReviewDTO ownerReview) {

		Long id = ownerReview.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			ownerReview.setId(id);
		}

		this.ownerReviews.put(id, ownerReview);
		return ownerReview;
	}

	@Override
	public OwnerReviewDTO findOne(Long id) {
		return this.ownerReviews.get(id);
	}

	@Override
	public OwnerReviewDTO update(OwnerReviewDTO ownerReview) {
		Long id = ownerReview.getId();

		if (id != null) {
			this.ownerReviews.put(id, ownerReview);
		}

		return ownerReview;
	}

	@Override
	public void delete(Long id) {
		this.ownerReviews.remove(id);
	}

}
