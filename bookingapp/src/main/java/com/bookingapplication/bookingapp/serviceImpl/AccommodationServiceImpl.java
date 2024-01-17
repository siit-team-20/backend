package com.bookingapplication.bookingapp.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.repositoryImpl.InMemoryAccommodationRepository;
import com.bookingapplication.bookingapp.service.AccommodationService;

@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private InMemoryAccommodationRepository accommodationRepository;

	@Override
	public Collection<AccommodationDTO> findAll() {
		Collection<AccommodationDTO> greetings = accommodationRepository.findAll();
		return greetings;
	}

	@Override
	public AccommodationDTO findOne(Long id) {
		AccommodationDTO greeting = accommodationRepository.findOne(id);
		return greeting;
	}

	@Override
	public AccommodationDTO create(AccommodationDTO accommodation) throws Exception {
		if (accommodation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationDTO savedAccommodation = accommodationRepository.create(accommodation);
		return savedAccommodation;
	}
	
	@Override
	public AccommodationDTO update(AccommodationDTO accommodation) throws Exception {
		AccommodationDTO accommodationToUpdate = findOne(accommodation.getId());
		if (accommodationToUpdate == null) {
			throw new Exception("Trazeni entitet nije pronadjen.");
		}
		accommodationToUpdate.setOwnerEmail(accommodation.getOwnerEmail());
		accommodationToUpdate.setName(accommodation.getName());
		accommodationToUpdate.setDescription(accommodation.getDescription());
		accommodationToUpdate.setLocation(accommodation.getLocation());
		accommodationToUpdate.setMinGuests(accommodation.getMinGuests());
		accommodationToUpdate.setMaxGuests(accommodation.getMaxGuests());
		accommodationToUpdate.setAccommodationType(accommodation.getAccommodationType());
		accommodationToUpdate.setBenefits(accommodation.getBenefits());
		accommodationToUpdate.setAvailabilityStart(accommodation.getAvailabilityStart());
		accommodationToUpdate.setAvailabilityEnd(accommodation.getAvailabilityEnd());
		accommodationToUpdate.setIsApproved(accommodation.getIsApproved());
		accommodationToUpdate.setIsPriceByGuest(accommodation.getIsPriceByGuest());
		accommodationToUpdate.setIsAutomaticAcceptance(accommodation.getIsAutomaticAcceptance());
		accommodationToUpdate.setPrice(accommodation.getPrice());
		accommodationToUpdate.setReservationCancellationDeadline(accommodation.getReservationCancellationDeadline());
		//accommodationToUpdate.setPictures(accommodation.getPictures());
		AccommodationDTO updatedAccommodation = accommodationRepository.create(accommodationToUpdate);
		return updatedAccommodation;
	}

	@Override
	public void delete(Long id) {
		accommodationRepository.delete(id);
	}

}
