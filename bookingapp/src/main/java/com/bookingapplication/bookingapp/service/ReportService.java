package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.ReportDTO;

public interface ReportService {

	Collection<ReportDTO> findAll();

	ReportDTO findOne(Long id);

	ReportDTO create(ReportDTO report) throws Exception;

	ReportDTO update(ReportDTO report, Long id) throws Exception;

	void delete(Long id);
	
	Report toReport(ReportDTO ReportDTO);

	ReportDTO toReportDTO(Report report);

	List<ReportDTO> toReportDTOs(List<Report> reports);

	void updateReport(Report target, Report source);
	
}
