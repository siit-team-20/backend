package com.bookingapplication.bookingapp.dtos;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.domain.Report;

public class ReportDTO {
	
	private Long id;
	private String reporterEmail;
	private String reportedEmail;
	private Long accommodationId;

	public ReportDTO() {
		// TODO Auto-generated constructor stub
	}
	public ReportDTO(Long id, String reporterEmail, String reportedEmail, long accommodationId) {
		this.id = id;
		this.reporterEmail = reporterEmail;
		this.reportedEmail = reportedEmail;
		this.accommodationId = accommodationId;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public String getReportedEmail() {
		return reportedEmail;
	}

	public void setReportedEmail(String reportedEmail) {
		this.reportedEmail = reportedEmail;
	}

	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	public void copyValues(ReportDTO report) {
		this.accommodationId = report.getAccommodationId();
		this.reportedEmail = report.getReportedEmail();
		this.reporterEmail = report.getReporterEmail();
	}

}
