package com.bookingapplication.bookingapp.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.repositoryImpl.InMemoryFavouriteAccommodationRepository;
import com.bookingapplication.bookingapp.service.FavouriteAccommodationService;

@Service
public class FavouriteAccommodationServiceImpl implements FavouriteAccommodationService {

	@Autowired
	private InMemoryFavouriteAccommodationRepository favouriteAccommodationRepository;

	@Override
	public Collection<FavouriteAccommodationDTO> findAll() {
		Collection<FavouriteAccommodationDTO> greetings = favouriteAccommodationRepository.findAll();
		return greetings;
	}

	@Override
	public FavouriteAccommodationDTO findOne(Long id) {
		FavouriteAccommodationDTO greeting = favouriteAccommodationRepository.findOne(id);
		return greeting;
	}

	@Override
	public FavouriteAccommodationDTO create(FavouriteAccommodationDTO favouriteAccommodation) throws Exception {
		if (favouriteAccommodation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		FavouriteAccommodationDTO savedAccommodationRequest = favouriteAccommodationRepository.create(favouriteAccommodation);
		return savedAccommodationRequest;
	}

	@Override
	public FavouriteAccommodationDTO update(FavouriteAccommodationDTO favouriteAccommodation) throws Exception {
		FavouriteAccommodationDTO favouriteAccommodationToUpdate = findOne(favouriteAccommodation.getId());
		if (favouriteAccommodationToUpdate == null) {
			throw new Exception("Trazeni entitet nije pronadjen.");
		}
		favouriteAccommodationToUpdate.setGuestid(favouriteAccommodation.getGuestid());
		favouriteAccommodationToUpdate.setAccommodationId(favouriteAccommodation.getGuestid());
		FavouriteAccommodationDTO updatedFavouriteAccommodation = favouriteAccommodationRepository.create(favouriteAccommodationToUpdate);
		return updatedFavouriteAccommodation;
	}

	@Override
	public void delete(Long id) {
		favouriteAccommodationRepository.delete(id);
	}

}

