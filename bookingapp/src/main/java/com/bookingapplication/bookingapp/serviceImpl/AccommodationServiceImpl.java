package com.bookingapplication.bookingapp.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationService;

import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.mappers.AccommodationMapper;
import com.bookingapplication.bookingapp.mappers.AccommodationMapperImpl;

@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepositoryJpa accommodationRepositoryJpa;
	private AccommodationMapper accommodationMapper;
	
	public AccommodationServiceImpl() {
		this.accommodationMapper = new AccommodationMapperImpl();
	}

	@Override
	public Collection<AccommodationDTO> findAll() {
		return accommodationMapper.toAccommodationDtos(accommodationRepositoryJpa.findAll());
	}

	@Override
	public AccommodationDTO findOne(Long id) {
		Accommodation accommodation = accommodationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Accommodation not found", HttpStatus.NOT_FOUND));
        return accommodationMapper.toAccommodationDTO(accommodation);
	}

	@Override
	public AccommodationDTO create(AccommodationDTO accommodationDTO) throws Exception {
		Accommodation accommodation = accommodationMapper.toAccommodation(accommodationDTO);
		if (accommodation.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Accommodation savedAccommodation = accommodationRepositoryJpa.save(accommodation);
		return accommodationMapper.toAccommodationDTO(savedAccommodation);
	}
	
	@Override
	public AccommodationDTO update(AccommodationDTO accommodationDTO, Long id) throws Exception {
		Accommodation accommodation = accommodationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Accommodation not found", HttpStatus.NOT_FOUND));

		accommodationMapper.updateAccommodation(accommodation, accommodationMapper.toAccommodation(accommodationDTO));

        Accommodation savedAccommodation = accommodationRepositoryJpa.save(accommodation);

        return accommodationMapper.toAccommodationDTO(savedAccommodation);
	}

	@Override
	public void delete(Long id) {
		accommodationRepositoryJpa.deleteById(id);
	}

}
