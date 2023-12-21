package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.repository.FavouriteAccommodationRepository;

@Repository
public class InMemoryFavouriteAccommodationRepository implements FavouriteAccommodationRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, FavouriteAccommodationDTO> favouriteAccommodations = new ConcurrentHashMap<Long, FavouriteAccommodationDTO>();

	@Override
	public Collection<FavouriteAccommodationDTO> findAll() {
		return this.favouriteAccommodations.values();
	}

	@Override
	public FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation) {
		Long id = favouriteAccommodation.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			favouriteAccommodation.setId(id);
		}

		this.favouriteAccommodations.put(id, favouriteAccommodation);
		return favouriteAccommodation;
	}

	@Override
	public FavouriteAccommodationDTO findOne(Long id) {
		return this.favouriteAccommodations.get(id);
	}

	@Override
	public void delete(Long id) {
		this.favouriteAccommodations.remove(id);
	}

	@Override
	public FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation) {
		Long id = favouriteAccommodation.getId();

		if (id != null) {
			this.favouriteAccommodations.put(id, favouriteAccommodation);
		}

		return favouriteAccommodation;
	}

}


