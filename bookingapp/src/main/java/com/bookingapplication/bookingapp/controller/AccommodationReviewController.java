package com.bookingapplication.bookingapp.controller;

import java.util.Collection;

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

import com.bookingapplication.bookingapp.domain.AccommodationReview;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationReviewDTO;
import com.bookingapplication.bookingapp.service.AccommodationReviewService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/accommodations/reviews")
public class AccommodationReviewController {
	
	@Autowired
	private AccommodationReviewService accommodationReviewService;

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @@GetMapping anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju GET zahteva
	 * atribut 'produce' sa naznakom tipa odgovora (u nasem slucaju JSON).
	 * 
	 * Kao povratna vrednost moze se vracati klasa ResponseEntity koja sadrzi i telo
	 * (sam podatak) i zaglavlje (metapodatke) i status kod, ili samo telo ako se
	 * metoda anotira sa @ResponseBody.
	 * 
	 * url: /api/accommodationReviews GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AccommodationReviewDTO>> getAccommodationReviews(@RequestParam(required = false) String ownerEmail, @RequestParam(required = false) String accommodationId, @RequestParam(required = false) String onlyNotApproved) {
		Collection<AccommodationReviewDTO> accommodationReviews = accommodationReviewService.findAll();
		if (ownerEmail != null)
			accommodationReviews = accommodationReviewService.findAll(ownerEmail);
		else if (accommodationId != null && onlyNotApproved != null)
			accommodationReviews = accommodationReviewService.findAll(Long.valueOf(accommodationId), Boolean.valueOf(onlyNotApproved));
		else if (onlyNotApproved != null)
			accommodationReviews = accommodationReviewService.findAll(Boolean.valueOf(onlyNotApproved));
		else
			accommodationReviews = accommodationReviewService.findAll();
		return new ResponseEntity<Collection<AccommodationReviewDTO>>(accommodationReviews, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/accommodationReviews/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReviewDTO> getAccommodationReview(@PathVariable("id") Long id) {
		AccommodationReviewDTO accommodationReview = accommodationReviewService.findOne(id);

		if (accommodationReview == null) {
			return new ResponseEntity<AccommodationReviewDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccommodationReviewDTO>(accommodationReview, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
	 * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
	 * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
	 * 
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
	 * podatka da napravi objekat tipa AccommodationReview.
	 * 
	 * url: /api/accommodationReviews POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReviewDTO> createAccommodationReview(@RequestBody AccommodationReviewDTO accommodationReview) throws Exception {
		AccommodationReviewDTO savedAccommodationReview = accommodationReviewService.create(accommodationReview);
		return new ResponseEntity<AccommodationReviewDTO>(savedAccommodationReview, HttpStatus.CREATED);
	}

	/*
	 * url: /api/accommodationReviews/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationReviewDTO> updateAccommodationReview(@RequestBody AccommodationReviewDTO accommodationReview, @PathVariable Long id)
			throws Exception {
		AccommodationReviewDTO accommodationReviewForUpdate = accommodationReviewService.findOne(id);
		accommodationReviewForUpdate.copyValues(accommodationReview);

		AccommodationReviewDTO updatedAccommodationReview = accommodationReviewService.update(accommodationReviewForUpdate, id);

		if (updatedAccommodationReview == null) {
			return new ResponseEntity<AccommodationReviewDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AccommodationReviewDTO>(updatedAccommodationReview, HttpStatus.OK);
	}

	/*
	 * url: /api/accommodationReviews/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationReviewDTO> deleteAccommodationReview(@PathVariable("id") Long id) {
		accommodationReviewService.delete(id);
		return new ResponseEntity<AccommodationReviewDTO>(HttpStatus.NO_CONTENT);
	}

}
