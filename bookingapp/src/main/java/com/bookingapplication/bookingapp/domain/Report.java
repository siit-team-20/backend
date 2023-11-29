package com.bookingapplication.bookingapp.domain;

public class Report {
	
	private String reporterEmail;
	private String reportedEmail;
	private Long accommodationId;
	
	public Report() {
		
	}
	
	public Report(String reporterEmail, String reportedEmail, Long accommodationId) {
		this.reporterEmail = reporterEmail;
		this.reportedEmail = reportedEmail;
		this.accommodationId = accommodationId;
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
    
    public void copyValues(Report report) {
		this.reporterEmail = report.getReporterEmail();
		this.reportedEmail = report.getReportedEmail();
		this.accommodationId = report.getAccommodationId();
    }
	
}
