package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Report;

public interface ReportService {

	Collection<Report> findAll();

	Report findOne(Long id);

	Report create(Report report) throws Exception;

	Report update(Report report) throws Exception;

	void delete(Long id);
	
}
