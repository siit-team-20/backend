package com.bookingapplication.bookingapp.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.service.AccommodationReservationService;

@RestController
@RequestMapping("/api/accommodations/reservations")
public class AccommodationReservationController {

	@Autowired
	private AccommodationReservationService accommodationReservationService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AccommodationReservation>> getAccommodationReservations() {
		Collection<AccommodationReservation> accommodationReservations = accommodationReservationService.findAll();
		return new ResponseEntity<Collection<AccommodationReservation>>(accommodationReservations, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservation> getAccommodationReservation(@PathVariable("id") Long id) {
		AccommodationReservation accommodaitonReservation = accommodationReservationService.findOne(id);

		if (accommodaitonReservation == null) {
			return new ResponseEntity<AccommodationReservation>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccommodationReservation>(accommodaitonReservation, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservation> createAccommodationReservation(@RequestBody AccommodationReservation accommodationReservation) throws Exception {
		AccommodationReservation savedAccommodationReservation = accommodationReservationService.create(accommodationReservation);
		return new ResponseEntity<AccommodationReservation>(savedAccommodationReservation, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReservation> updateAccommodationReservation(@RequestBody AccommodationReservation accommodationReservation, @PathVariable Long id)
			throws Exception {
		AccommodationReservation accommodationReservationForUpdate = accommodationReservationService.findOne(id);
		accommodationReservationForUpdate.copyValues(accommodationReservation);

		AccommodationReservation updatedAccommodationReservation = accommodationReservationService.update(accommodationReservationForUpdate);

		if (updatedAccommodationReservation == null) {
			return new ResponseEntity<AccommodationReservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AccommodationReservation>(updatedAccommodationReservation, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationReservation> deleteAccommodationReservation(@PathVariable("id") Long id) {
		accommodationReservationService.delete(id);
		return new ResponseEntity<AccommodationReservation>(HttpStatus.NO_CONTENT);
	}

}
