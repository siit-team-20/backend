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

import com.bookingapplication.bookingapp.service.FavouriteAccommodationService;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationWithAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.ReservationWithAccommodationDTO;

@RestController
@RequestMapping("/api/accommodations/favourites")
public class FavouriteAccommodationController {

	@Autowired
	private FavouriteAccommodationService favouriteAccommodationService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FavouriteAccommodationWithAccommodationDTO>> getFavouriteAccommodations(@RequestParam(required = false) String guestEmail) {
		Collection<FavouriteAccommodationWithAccommodationDTO> favouriteAccommodations = new ArrayList<FavouriteAccommodationWithAccommodationDTO>();
		if (guestEmail != null)
			favouriteAccommodations = favouriteAccommodationService.findAll(guestEmail);
		else
			favouriteAccommodations = favouriteAccommodationService.findAll();
		return new ResponseEntity<Collection<FavouriteAccommodationWithAccommodationDTO>>(favouriteAccommodations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodationDTO> getFavouriteAccommodation(@PathVariable("id") Long id) {
		FavouriteAccommodationDTO favouriteAccommodation = favouriteAccommodationService.findOne(id);

		if (favouriteAccommodation == null) {
			return new ResponseEntity<FavouriteAccommodationDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<FavouriteAccommodationDTO>(favouriteAccommodation, HttpStatus.OK);
	}


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodationDTO> createFavouriteAccommodation(@RequestBody FavouriteAccommodationWithAccommodationDTO favouriteAccommodation) throws Exception {
		FavouriteAccommodationDTO savedFavouriteAccommodation = favouriteAccommodationService.create(favouriteAccommodation);
		return new ResponseEntity<FavouriteAccommodationDTO>(savedFavouriteAccommodation, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodationDTO> updateFavouriteAccommodation(@RequestBody FavouriteAccommodationDTO FavouriteAccommodation, @PathVariable Long id)
			throws Exception {
		FavouriteAccommodationDTO favouriteAccommodationForUpdate = favouriteAccommodationService.findOne(id);
		favouriteAccommodationForUpdate.copyValues(FavouriteAccommodation);

		FavouriteAccommodationDTO updatedFavouriteAccommodation = favouriteAccommodationService.update(favouriteAccommodationForUpdate, id);

		if (updatedFavouriteAccommodation == null) {
			return new ResponseEntity<FavouriteAccommodationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<FavouriteAccommodationDTO>(updatedFavouriteAccommodation, HttpStatus.OK);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FavouriteAccommodationDTO> deleteFavouriteAccommodation(@PathVariable("id") Long id) {
		favouriteAccommodationService.delete(id);
		return new ResponseEntity<FavouriteAccommodationDTO>(HttpStatus.NO_CONTENT);
	}

}
