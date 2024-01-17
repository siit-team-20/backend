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

import com.bookingapplication.bookingapp.dtos.AccommodationRequestDTO;
import com.bookingapplication.bookingapp.service.AccommodationRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/accommodations/requests")
public class AccommodationRequestController {
	
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
	 * url: /api/accommodations/requests GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AccommodationRequestDTO>> getAccommodationRequests() {
		Collection<AccommodationRequestDTO> accommodationRequests = accommodationRequestService.findAll();
		return new ResponseEntity<Collection<AccommodationRequestDTO>>(accommodationRequests, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/accommodations/requests/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationRequestDTO> getAccommodationRequest(@PathVariable("id") Long id) {
		AccommodationRequestDTO accommodationRequest = accommodationRequestService.findOne(id);

		if (accommodationRequest == null) {
			return new ResponseEntity<AccommodationRequestDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccommodationRequestDTO>(accommodationRequest, HttpStatus.OK);
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
	 * url: /api/accommodations/requests POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationRequestDTO> createAccommodationRequest(@RequestBody AccommodationRequestDTO accommodationRequest) throws Exception {
		AccommodationRequestDTO savedAccommodationRequest = accommodationRequestService.create(accommodationRequest);
		return new ResponseEntity<AccommodationRequestDTO>(savedAccommodationRequest, HttpStatus.CREATED);
	}

	/*
	 * url: /api/accommodations/requests/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccommodationRequestDTO> updateAccommodationRequest(@RequestBody AccommodationRequestDTO accommodationRequest, @PathVariable Long id)
			throws Exception {
		AccommodationRequestDTO accommodationRequestForUpdate = accommodationRequestService.findOne(id);
		accommodationRequestForUpdate.copyValues(accommodationRequest);

		AccommodationRequestDTO updatedAccommodationRequest = accommodationRequestService.update(accommodationRequestForUpdate, id);

		if (updatedAccommodationRequest == null) {
			return new ResponseEntity<AccommodationRequestDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AccommodationRequestDTO>(updatedAccommodationRequest, HttpStatus.OK);
	}

	/*
	 * url: /api/accommodatios/requests/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccommodationRequestDTO> deleteAccommodationRequest(@PathVariable("id") Long id) {
		accommodationRequestService.delete(id);
		return new ResponseEntity<AccommodationRequestDTO>(HttpStatus.NO_CONTENT);
	}

}
