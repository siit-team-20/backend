package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Report;

public interface ReportRepository {

	Collection<Report> findAll();

	Report create(Report user);

	Report findOne(Long id);
	
	Report update(Report user);

	void delete(Long id);
	
}
