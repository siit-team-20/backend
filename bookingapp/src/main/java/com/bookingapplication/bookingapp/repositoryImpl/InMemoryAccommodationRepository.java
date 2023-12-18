package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.repository.AccommodationRepository;

@Repository
public class InMemoryAccommodationRepository implements AccommodationRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, AccommodationDTO> accommodations = new ConcurrentHashMap<Long, AccommodationDTO>();

	@Override
	public Collection<AccommodationDTO> findAll() {
		return this.accommodations.values();
	}

	@Override
	public AccommodationDTO create(AccommodationDTO accommodation) {

		Long id = accommodation.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			accommodation.setId(id);
		}

		this.accommodations.put(id, accommodation);
		return accommodation;
	}

	@Override
	public AccommodationDTO findOne(Long id) {
		return this.accommodations.get(id);
	}

	@Override
	public void delete(Long id) {
		this.accommodations.remove(id);
	}

	@Override
	public AccommodationDTO update(AccommodationDTO accommodation) {
		Long id = accommodation.getId();

		if (id != null) {
			this.accommodations.put(id, accommodation);
		}

		return accommodation;
	}

}

