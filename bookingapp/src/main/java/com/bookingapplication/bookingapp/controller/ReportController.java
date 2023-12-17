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

import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	//@Autowired
	private ReportService reportService;

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
	 * url: /api/reports GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Report>> getUsers() {
		Collection<Report> reports = reportService.findAll();
		return new ResponseEntity<Collection<Report>>(reports, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/reports/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Report> getUser(@PathVariable("id") Long id) {
		Report report = reportService.findOne(id);

		if (report == null) {
			return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Report>(report, HttpStatus.OK);
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
	 * url: /api/reports POST
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Report> createGreeting(@RequestBody Report report) throws Exception {
		Report savedReport = reportService.create(report);
		return new ResponseEntity<Report>(savedReport, HttpStatus.CREATED);
	}

	/*
	 * url: /api/reports/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Report> updateGreeting(@RequestBody Report report, @PathVariable Long id)
			throws Exception {
		Report reportForUpdate = reportService.findOne(id);
		reportForUpdate.copyValues(report);

		Report updatedReport = reportService.update(reportForUpdate);

		if (updatedReport == null) {
			return new ResponseEntity<Report>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Report>(updatedReport, HttpStatus.OK);
	}

	/*
	 * url: /api/reports/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Report> deleteGreeting(@PathVariable("id") Long id) {
		reportService.delete(id);
		return new ResponseEntity<Report>(HttpStatus.NO_CONTENT);
	}
	
}
