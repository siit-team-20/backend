package com.bookingapplication.bookingapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String reporterEmail;
	@Column
	private String reportedEmail;
	
	public Report() {
		
	}
	
	public Report(Long id, String reporterEmail, String reportedEmail) {
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
    
    public void copyValues(Report report) {
		this.reporterEmail = report.getReporterEmail();
		this.reportedEmail = report.getReportedEmail();
    }
	
}
