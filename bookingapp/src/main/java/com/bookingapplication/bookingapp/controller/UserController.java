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

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;
import com.bookingapplication.bookingapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//@Autowired
	private UserService userService;

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
	 * url: /api/users GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> users = userService.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/users/andrija.slovic1@gmail.com GET
	 */
	@GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
		User user = userService.findOne(email);

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/users/owner GET
	 */
	@GetMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsersByType(@PathVariable("type") UserType type) {
		Collection<User> users = userService.findByType(type);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
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
	 * url: /api/users POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createGreeting(@RequestBody User user) throws Exception {
		User savedUser = userService.create(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	/*
	 * url: /api/users/andrija.slovic1@gmail.com PUT
	 */
	@PutMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateGreeting(@RequestBody User user, @PathVariable String email)
			throws Exception {
		User userForUpdate = userService.findOne(email);
		userForUpdate.copyValues(user);

		User updatedUser = userService.update(userForUpdate);

		if (updatedUser == null) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	/*
	 * url: /api/users/andrija.slovic1@gmail.com DELETE
	 */
	@DeleteMapping(value = "/{email}")
	public ResponseEntity<User> deleteGreeting(@PathVariable("email") String email) {
		userService.delete(email);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
