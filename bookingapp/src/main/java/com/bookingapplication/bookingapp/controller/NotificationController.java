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

import com.bookingapplication.bookingapp.domain.Notification;
import com.bookingapplication.bookingapp.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

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
	 * url: /api/notificatons GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Notification>> getUsers() {
		Collection<Notification> notifications = notificationService.findAll();
		return new ResponseEntity<Collection<Notification>>(notifications, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/notifications/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> getUser(@PathVariable("id") Long id) {
		Notification notification = notificationService.findOne(id);

		if (notification == null) {
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
	 * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
	 * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
	 * 
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
	 * podatka da napravi objekat tipa Greeting.
	 * 
	 * url: /api/notifications POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> createGreeting(@RequestBody Notification notification) throws Exception {
		Notification savedNotification = notificationService.create(notification);
		return new ResponseEntity<Notification>(savedNotification, HttpStatus.CREATED);
	}

	/*
	 * url: /api/notifications/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> updateGreeting(@RequestBody Notification notification, @PathVariable Long id)
			throws Exception {
		Notification notificationForUpdate = notificationService.findOne(id);
		notificationForUpdate.copyValues(notification);

		Notification updatedNotification = notificationService.update(notificationForUpdate);

		if (updatedNotification == null) {
			return new ResponseEntity<Notification>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Notification>(updatedNotification, HttpStatus.OK);
	}

	/*
	 * url: /api/notifications/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Notification> deleteGreeting(@PathVariable("id") Long id) {
		notificationService.delete(id);
		return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
	}
	
}
