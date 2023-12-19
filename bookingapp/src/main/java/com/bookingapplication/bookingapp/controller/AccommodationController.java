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
import org.springframework.web.bind.annotation.RestController;

import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;
import com.bookingapplication.bookingapp.service.AccommodationRequestService;
import com.bookingapplication.bookingapp.service.AccommodationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/accommodations")
public class AccommodationController {
	
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private AccommodationRequestService accommodationRequestService;

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
	public ResponseEntity<Collection<AccommodationDTO>> getAccommodations() {
		Collection<AccommodationDTO> accommodations = accommodationService.findAll();
		return new ResponseEntity<Collection<AccommodationDTO>>(accommodations, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/accommodation/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
		AccommodationDTO accommodation = accommodationService.findOne(id);

		if (accommodation == null) {
			return new ResponseEntity<AccommodationDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccommodationDTO>(accommodation, HttpStatus.OK);
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
	public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodation) throws Exception {
		AccommodationDTO savedAccommodation = accommodationService.create(accommodation);
		accommodationRequestService.create(savedAccommodation, AccommodationRequestType.Created);
		return new ResponseEntity<AccommodationDTO>(savedAccommodation, HttpStatus.CREATED);
	}

	/*
	 * url: /api/accommodation/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodation, @PathVariable Long id)
			throws Exception {
		AccommodationDTO accommodationForUpdate = accommodationService.findOne(id);
		accommodationForUpdate.copyValues(accommodation);

		AccommodationDTO updatedAccommodation = accommodationService.update(accommodationForUpdate, id);

		if (updatedAccommodation == null) {
			return new ResponseEntity<AccommodationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AccommodationDTO>(updatedAccommodation, HttpStatus.OK);
	}

	/*
	 * url: /api/accommodation/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationDTO> deleteAccommodation(@PathVariable("id") Long id) {
		accommodationService.delete(id);
		return new ResponseEntity<AccommodationDTO>(HttpStatus.NO_CONTENT);
	}

}
