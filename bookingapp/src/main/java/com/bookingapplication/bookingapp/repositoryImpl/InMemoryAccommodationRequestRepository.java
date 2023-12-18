package com.bookingapplication.bookingapp.repositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.domain.AccommodationType;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;
import com.bookingapplication.bookingapp.repository.AccommodationRequestRepository;

@Repository
public class InMemoryAccommodationRequestRepository implements AccommodationRequestRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, AccommodationRequestDTO> accommodationRequests = new ConcurrentHashMap<Long, AccommodationRequestDTO>();

	@Override
	public Collection<AccommodationRequestDTO> findAll() {
		return this.accommodationRequests.values();
	}

	@Override
	public AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequest) {
		Long id = accommodationRequest.id;

		if (id == null) {
			id = counter.incrementAndGet();
			accommodationRequest.id = id;
		}

		this.accommodationRequests.put(id, accommodationRequest);
		return accommodationRequest;
	}

	@Override
	public AccommodationRequestDTO findOne(Long id) {
		return this.accommodationRequests.get(id);
	}

	@Override
	public void delete(Long id) {
		this.accommodationRequests.remove(id);
	}

	@Override
	public AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequest) {
		Long id = accommodationRequest.id;

		if (id != null) {
			this.accommodationRequests.put(id, accommodationRequest);
		}

		return accommodationRequest;
	}

}

