package com.bookingapplication.bookingapp.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.service.AccommodationReservationService;

@RestController
@RequestMapping("/api/accommodations/reservations")
public class AccommodationReservationController {

	@Autowired
	private AccommodationReservationService accommodationReservationService;
	
	public void updateReservations() {
		accommodationReservationService.updateStatuses();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AccommodationReservationDTO>> getAccommodationReservations(@RequestParam(required = false) String guestEmail) {
		updateReservations();
		Collection<AccommodationReservationDTO> accommodationReservations = new ArrayList<AccommodationReservationDTO>();
		if (guestEmail != null)
			accommodationReservations = accommodationReservationService.findAll(guestEmail);
		else
			accommodationReservations = accommodationReservationService.findAll();
		return new ResponseEntity<Collection<AccommodationReservationDTO>>(accommodationReservations, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservationDTO> getAccommodationReservation(@PathVariable("id") Long id) {
		updateReservations();
		AccommodationReservationDTO accommodaitonReservation = accommodationReservationService.findOne(id);

		if (accommodaitonReservation == null) {
			return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccommodationReservationDTO>(accommodaitonReservation, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservationDTO> createAccommodationReservation(@RequestBody AccommodationReservationDTO accommodationReservation) throws Exception {
		updateReservations();
		AccommodationReservationDTO savedAccommodationReservation = accommodationReservationService.create(accommodationReservation);
		return new ResponseEntity<AccommodationReservationDTO>(savedAccommodationReservation, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservationDTO> updateAccommodationReservation(@RequestBody AccommodationReservationDTO accommodationReservation, @PathVariable Long id)
			throws Exception {
		updateReservations();
		AccommodationReservationDTO accommodationReservationForUpdate = accommodationReservationService.findOne(id);
		accommodationReservationForUpdate.copyValues(accommodationReservation);

		AccommodationReservationDTO updatedAccommodationReservation = accommodationReservationService.update(accommodationReservationForUpdate, id);

		if (updatedAccommodationReservation == null) {
			return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AccommodationReservationDTO>(updatedAccommodationReservation, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationReservationDTO> deleteAccommodationReservation(@PathVariable("id") Long id) {
		updateReservations();
		accommodationReservationService.delete(id);
		return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.NO_CONTENT);
	}

}
