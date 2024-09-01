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

import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.ReportDTO;
import com.bookingapplication.bookingapp.service.ReportService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reports")

public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ReportDTO>> getUsers() {
		Collection<ReportDTO> reports = reportService.findAll();
		return new ResponseEntity<Collection<ReportDTO>>(reports, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/reports/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> getUser(@PathVariable("id") Long id) {
		ReportDTO report = reportService.findOne(id);

		if (report == null) {
			return new ResponseEntity<ReportDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ReportDTO>(report, HttpStatus.OK);
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
	public ResponseEntity<ReportDTO> create(@RequestBody ReportDTO report) throws Exception {
		ReportDTO savedReport = reportService.create(report);
		return new ResponseEntity<ReportDTO>(savedReport, HttpStatus.CREATED);
	}

	/*
	 * url: /api/reports/1 PUT
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> update(@RequestBody ReportDTO report, @PathVariable Long id)
			throws Exception {
		ReportDTO reportForUpdate = reportService.findOne(id);
		reportForUpdate.copyValues(report);

		ReportDTO updatedReport = reportService.update(reportForUpdate, id);

		if (updatedReport == null) {
			return new ResponseEntity<ReportDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ReportDTO>(updatedReport, HttpStatus.OK);
	}

	/*
	 * url: /api/reports/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ReportDTO> delete(@PathVariable("id") Long id) {
		reportService.delete(id);
		return new ResponseEntity<ReportDTO>(HttpStatus.NO_CONTENT);
	}
	
}
