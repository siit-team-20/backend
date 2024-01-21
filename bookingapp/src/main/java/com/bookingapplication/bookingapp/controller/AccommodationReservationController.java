package com.bookingapplication.bookingapp.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.boot.model.internal.Nullability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapplication.bookingapp.domain.ReservationStatus;
import com.bookingapplication.bookingapp.dtos.AccommodationReservationDTO;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationWithAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;
import com.bookingapplication.bookingapp.service.AccommodationReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/accommodations/reservations")
public class AccommodationReservationController {

	@Autowired
	private AccommodationReservationService accommodationReservationService;
	
	public void updateReservations() {
		accommodationReservationService.updateStatuses();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ReservationWithAccommodationDTO>> getAccommodationReservations(@RequestParam(required = false) String guestEmail, @RequestParam(required = false) String ownerEmail, @RequestParam(required = false) ReservationStatus status, @RequestParam(required = false) Long days, @RequestParam(required = false) Long accommodationId) {
		updateReservations();
		Collection<ReservationWithAccommodationDTO> accommodationReservations = new ArrayList<ReservationWithAccommodationDTO>();
		if (ownerEmail != null && status != null && guestEmail != null)
			accommodationReservations = accommodationReservationService.findAll(ownerEmail, guestEmail, status);
		else if (ownerEmail != null && status != null)
			accommodationReservations = accommodationReservationService.findAllByOwnerEmailAndStatus(ownerEmail, status);
		else if (guestEmail != null && status != null && days != null && accommodationId != null)
			accommodationReservations = accommodationReservationService.findAll(guestEmail, status, days, accommodationId);
		else if (guestEmail != null && status != null)
			accommodationReservations = accommodationReservationService.findAll(guestEmail, status);
		else if (status != null && accommodationId != null)
			accommodationReservations = accommodationReservationService.findAll(accommodationId, status);
		else if (accommodationId != null)
			accommodationReservations = accommodationReservationService.findAll(accommodationId);
		else if (guestEmail != null)
			accommodationReservations = accommodationReservationService.findAll(guestEmail);
		else if (ownerEmail != null)
			accommodationReservations = accommodationReservationService.findAllByOwnerEmail(ownerEmail);
		else
			accommodationReservations = accommodationReservationService.findAll();
		return new ResponseEntity<Collection<ReservationWithAccommodationDTO>>(accommodationReservations, HttpStatus.OK);
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
	
	@PutMapping
	public ResponseEntity<AccommodationReservationDTO> updateAccommodationReservation(@RequestParam(required = false) Long oldAccommodationId, @RequestParam(required = false) Long newAccommodationId) {
		updateReservations();
		if (oldAccommodationId != null && newAccommodationId != null) {
			Collection<ReservationWithAccommodationDTO> accommodationReservationDTOs = accommodationReservationService.findAll();
			for (ReservationWithAccommodationDTO reservationWithAccommodationDTO : accommodationReservationDTOs) {
				if (reservationWithAccommodationDTO.getAccommodation().getId().equals(oldAccommodationId)) {
					AccommodationReservationDTO reservation = accommodationReservationService.findOne(reservationWithAccommodationDTO.getId());
					reservation.setAccommodationId(newAccommodationId);
					try {
						accommodationReservationService.update(reservation, reservation.getId());
					} catch (Exception e) {
					}
				}
			}
		}
		return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationReservationDTO> deleteAccommodationReservation(@PathVariable("id") Long id) {
		updateReservations();
		accommodationReservationService.delete(id);
		return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<AccommodationReservationDTO> deleteAccommodationReservation(@RequestParam(required = false) String guestEmail, @RequestParam(required = false) ReservationStatus status, @RequestParam(required = false) String ownerEmail) {
		updateReservations();
		if (guestEmail != null && status != null)
			accommodationReservationService.deleteAll(guestEmail, status);
		else if (ownerEmail != null && status != null)
			accommodationReservationService.deleteAllByOwnerEmail(ownerEmail, status);
		return new ResponseEntity<AccommodationReservationDTO>(HttpStatus.NO_CONTENT);
	}

}
