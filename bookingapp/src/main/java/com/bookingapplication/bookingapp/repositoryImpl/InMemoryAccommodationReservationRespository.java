package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.repository.AccommodationReservationRepository;

@Repository
public class InMemoryAccommodationReservationRespository implements AccommodationReservationRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, AccommodationReservationDTO> accommodationReservations = new ConcurrentHashMap<Long, AccommodationReservationDTO>();

	@Override
	public Collection<AccommodationReservationDTO> findAll() {
		return this.accommodationReservations.values();
	}

	@Override
	public AccommodationReservationDTO create(AccommodationReservationDTO accommodationReservation) {

		Long id = accommodationReservation.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			accommodationReservation.setId(id);
		}

		this.accommodationReservations.put(id, accommodationReservation);
		return accommodationReservation;
	}

	@Override
	public AccommodationReservationDTO findOne(Long id) {
		return this.accommodationReservations.get(id);
	}

	@Override
	public AccommodationReservationDTO update(AccommodationReservationDTO accommodationReservation) {
		Long id = accommodationReservation.getId();

		if (id != null) {
			this.accommodationReservations.put(id, accommodationReservation);
		}

		return accommodationReservation;
	}

	@Override
	public void delete(Long id) {
		this.accommodationReservations.remove(id);
		
	}


}
