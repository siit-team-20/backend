package com.bookingapplication.bookingapp.dtos;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.domain.Report;

public class ReportDTO {
	
	private Long id;
	private String reporterEmail;
	private String reportedEmail;

	public ReportDTO() {
		// TODO Auto-generated constructor stub
	}
	public ReportDTO(Long id, String reporterEmail, String reportedEmail) {
		this.id = id;
		this.reporterEmail = reporterEmail;
		this.reportedEmail = reportedEmail;
		
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
	}
	public void copyValues(ReportDTO report) {
		this.reportedEmail = report.getReportedEmail();
		this.reporterEmail = report.getReporterEmail();
	}

}
