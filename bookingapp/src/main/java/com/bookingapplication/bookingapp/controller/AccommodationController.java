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

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.service.AccommodationService;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
	
	//@Autowired
	private AccommodationService accommodationService;

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
	 * url: /api/accommodations GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Accommodation>> getAccommodations() {
		Collection<Accommodation> accommodations = accommodationService.findAll();
		return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/accommodation/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Accommodation> getAccommodation(@PathVariable("id") Long id) {
		Accommodation accommodation = accommodationService.findOne(id);

		if (accommodation == null) {
			return new ResponseEntity<Accommodation>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Accommodation>(accommodation, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
	 * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
	 * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
	 * 
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
	 * podatka da napravi objekat tipa Accommodation.
	 * 
	 * url: /api/accommodation POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Accommodation> createAccommodation(@RequestBody Accommodation accommodation) throws Exception {
		Accommodation savedAccommodation = accommodationService.create(accommodation);
		return new ResponseEntity<Accommodation>(savedAccommodation, HttpStatus.CREATED);
	}

	/*
	 * url: /api/accommodation/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Accommodation> updateAccommodation(@RequestBody Accommodation accommodation, @PathVariable Long id)
			throws Exception {
		Accommodation accommodationForUpdate = accommodationService.findOne(id);
		accommodationForUpdate.copyValues(accommodation);

		Accommodation updatedAccommodation = accommodationService.update(accommodationForUpdate);

		if (updatedAccommodation == null) {
			return new ResponseEntity<Accommodation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Accommodation>(updatedAccommodation, HttpStatus.OK);
	}

	/*
	 * url: /api/accommodation/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id) {
		accommodationService.delete(id);
		return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
	}

}
