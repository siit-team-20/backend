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

import com.bookingapplication.bookingapp.domain.OwnerReview;
import com.bookingapplication.bookingapp.service.OwnerReviewService;

@RestController
@RequestMapping("/api/ownerReviews")
public class OwnerReviewController {
	
	//@Autowired
	private OwnerReviewService ownerReviewService;

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
	 * url: /api/greetings GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<OwnerReview>> getOwnerReviews() {
		Collection<OwnerReview> ownerReviews = ownerReviewService.findAll();
		return new ResponseEntity<Collection<OwnerReview>>(ownerReviews, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/greetings/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OwnerReview> getOwnerReview(@PathVariable("id") Long id) {
		OwnerReview ownerReview = ownerReviewService.findOne(id);

		if (ownerReview == null) {
			return new ResponseEntity<OwnerReview>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<OwnerReview>(ownerReview, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
	 * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
	 * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
	 * 
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
	 * podatka da napravi objekat tipa OwnerReview.
	 * 
	 * url: /api/greetings POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OwnerReview> createOwnerReview(@RequestBody OwnerReview ownerReview) throws Exception {
		OwnerReview savedReview = ownerReviewService.create(ownerReview);
		return new ResponseEntity<OwnerReview>(savedReview, HttpStatus.CREATED);
	}

	/*
	 * url: /api/greetings/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OwnerReview> updateOwnerReview(@RequestBody OwnerReview ownerReview, @PathVariable Long id)
			throws Exception {
		OwnerReview reviewForUpdate = ownerReviewService.findOne(id);
		reviewForUpdate.copyValues(ownerReview);

		OwnerReview updatedReview = ownerReviewService.update(reviewForUpdate);

		if (updatedReview == null) {
			return new ResponseEntity<OwnerReview>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OwnerReview>(updatedReview, HttpStatus.OK);
	}

	/*
	 * url: /api/greetings/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OwnerReview> deleteOwnerReview(@PathVariable("id") Long id) {
		ownerReviewService.delete(id);
		return new ResponseEntity<OwnerReview>(HttpStatus.NO_CONTENT);
	}

}
