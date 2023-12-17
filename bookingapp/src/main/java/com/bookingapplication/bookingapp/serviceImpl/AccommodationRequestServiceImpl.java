package com.bookingapplication.bookingapp.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.repositoryImpl.InMemoryAccommodationRequestRepository;
import com.bookingapplication.bookingapp.service.AccommodationRequestService;

@Service
public class AccommodationRequestServiceImpl implements AccommodationRequestService {

	@Autowired
	private InMemoryAccommodationRequestRepository accommodationRequestRepository;

	@Override
	public Collection<AccommodationRequestDTO> findAll() {
		Collection<AccommodationRequestDTO> greetings = accommodationRequestRepository.findAll();
		return greetings;
	}

	@Override
	public AccommodationRequestDTO findOne(Long id) {
		AccommodationRequestDTO greeting = accommodationRequestRepository.findOne(id);
		return greeting;
	}

	@Override
	public AccommodationRequestDTO create(AccommodationRequestDTO accommodationRequest) throws Exception {
		if (accommodationRequest.id != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationRequestDTO savedAccommodationRequest = accommodationRequestRepository.create(accommodationRequest);
		return savedAccommodationRequest;
	}

	@Override
	public AccommodationRequestDTO update(AccommodationRequestDTO accommodationRequest) throws Exception {
		AccommodationRequestDTO accommodationRequestToUpdate = findOne(accommodationRequest.id);
		if (accommodationRequestToUpdate == null) {
			throw new Exception("Trazeni entitet nije pronadjen.");
		}
		accommodationRequestToUpdate.oldAccommodation = accommodationRequest.oldAccommodation;
		accommodationRequestToUpdate.newAccommodation = accommodationRequest.newAccommodation;
		accommodationRequestToUpdate.type = accommodationRequest.type;
		AccommodationRequestDTO updatedAccommodationRequest = accommodationRequestRepository.create(accommodationRequestToUpdate);
		return updatedAccommodationRequest;
	}

	@Override
	public void delete(Long id) {
		accommodationRequestRepository.delete(id);
	}

}
