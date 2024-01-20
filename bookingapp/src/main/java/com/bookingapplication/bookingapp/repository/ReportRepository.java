package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.ReportDTO;

public interface ReportRepository {

	Collection<ReportDTO> findAll();

	ReportDTO create(ReportDTO user);

	ReportDTO findOne(Long id);
	
	ReportDTO update(ReportDTO user);

	void delete(Long id);
	
}
