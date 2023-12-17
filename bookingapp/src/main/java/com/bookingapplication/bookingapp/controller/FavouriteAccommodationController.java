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

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;
import com.bookingapplication.bookingapp.service.FavouriteAccommodationService;

@RestController
@RequestMapping("/api/accommodations/favourites")
public class FavouriteAccommodationController {

	//@Autowired
	private FavouriteAccommodationService favouriteAccommodationService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FavouriteAccommodation>> getFavouriteAccommodations() {
		Collection<FavouriteAccommodation> favouriteAccommodations = favouriteAccommodationService.findAll();
		return new ResponseEntity<Collection<FavouriteAccommodation>>(favouriteAccommodations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodation> getFavouriteAccommodation(@PathVariable("id") Long id) {
		FavouriteAccommodation favouriteAccommodation = favouriteAccommodationService.findOne(id);

		if (favouriteAccommodation == null) {
			return new ResponseEntity<FavouriteAccommodation>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<FavouriteAccommodation>(favouriteAccommodation, HttpStatus.OK);
	}


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodation> createFavouriteAccommodation(@RequestBody FavouriteAccommodation FavouriteAccommodation) throws Exception {
		FavouriteAccommodation savedFavouriteAccommodation = favouriteAccommodationService.create(FavouriteAccommodation);
		return new ResponseEntity<FavouriteAccommodation>(savedFavouriteAccommodation, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavouriteAccommodation> updateFavouriteAccommodation(@RequestBody FavouriteAccommodation FavouriteAccommodation, @PathVariable Long id)
			throws Exception {
		FavouriteAccommodation favouriteAccommodationForUpdate = favouriteAccommodationService.findOne(id);
		favouriteAccommodationForUpdate.copyValues(FavouriteAccommodation);

		FavouriteAccommodation updatedFavouriteAccommodation = favouriteAccommodationService.update(favouriteAccommodationForUpdate);

		if (updatedFavouriteAccommodation == null) {
			return new ResponseEntity<FavouriteAccommodation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<FavouriteAccommodation>(updatedFavouriteAccommodation, HttpStatus.OK);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FavouriteAccommodation> deleteFavouriteAccommodation(@PathVariable("id") Long id) {
		favouriteAccommodationService.delete(id);
		return new ResponseEntity<FavouriteAccommodation>(HttpStatus.NO_CONTENT);
	}

}
