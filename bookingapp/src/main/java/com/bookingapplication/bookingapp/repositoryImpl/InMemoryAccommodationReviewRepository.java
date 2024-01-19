package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;
import com.bookingapplication.bookingapp.dtos.AccommodationReviewDTO;
import com.bookingapplication.bookingapp.repository.AccommodationReviewRepository;

@Repository
public class InMemoryAccommodationReviewRepository implements AccommodationReviewRepository {
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, AccommodationReviewDTO> accommodationReviews = new ConcurrentHashMap<Long, AccommodationReviewDTO>();

	@Override
	public Collection<AccommodationReviewDTO> findAll() {
		return this.accommodationReviews.values();
	}

	@Override
	public AccommodationReviewDTO findOne(Long id) {
		return this.accommodationReviews.get(id);
	}

	@Override
	public AccommodationReviewDTO create(AccommodationReviewDTO accommodationReview) {

		Long id = accommodationReview.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			accommodationReview.setId(id);
		}

		this.accommodationReviews.put(id, accommodationReview);
		return accommodationReview;
	}

	@Override
	public AccommodationReviewDTO update(AccommodationReviewDTO accommodationReview) {
		Long id = accommodationReview.getId();

		if (id != null) {
			this.accommodationReviews.put(id, accommodationReview);
		}

		return accommodationReview;
	}

	@Override
	public void delete(Long id) {
		this.accommodationReviews.remove(id);
	}

}
