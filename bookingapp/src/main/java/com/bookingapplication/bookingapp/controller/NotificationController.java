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

import com.bookingapplication.bookingapp.dtos.FavouriteAccommodationWithAccommodationDTO;
import com.bookingapplication.bookingapp.dtos.NotificationDTO;
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
	public ResponseEntity<Collection<NotificationDTO>> getNotifications(@RequestParam(required = false) String userEmail) {
		Collection<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
		if (userEmail != null)
			notifications = notificationService.findAll(userEmail);
		else
			notifications = notificationService.findAll();
		return new ResponseEntity<Collection<NotificationDTO>>(notifications, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/notifications/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificationDTO> getNotification(@PathVariable("id") Long id) {
		NotificationDTO notification = notificationService.findOne(id);

		if (notification == null) {
			return new ResponseEntity<NotificationDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<NotificationDTO>(notification, HttpStatus.OK);
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
	public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notification) throws Exception {
		NotificationDTO savedNotification = notificationService.create(notification);
		return new ResponseEntity<NotificationDTO>(savedNotification, HttpStatus.CREATED);
	}

	/*
	 * url: /api/notifications/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notification, @PathVariable Long id)
			throws Exception {
		NotificationDTO notificationForUpdate = notificationService.findOne(id);
		notificationForUpdate.copyValues(notification);

		NotificationDTO updatedNotification = notificationService.update(notificationForUpdate, id);

		if (updatedNotification == null) {
			return new ResponseEntity<NotificationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<NotificationDTO>(updatedNotification, HttpStatus.OK);
	}

	/*
	 * url: /api/notifications/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<NotificationDTO> deleteNotification(@PathVariable("id") Long id) {
		notificationService.delete(id);
		return new ResponseEntity<NotificationDTO>(HttpStatus.NO_CONTENT);
	}
	
}
