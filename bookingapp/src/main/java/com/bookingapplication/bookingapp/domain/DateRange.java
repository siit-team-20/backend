package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class DateRange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private LocalDate startDate;
	@Column
	private LocalDate endDate;
	@Column
	private double price;
	@Column
	private Long accommodationId;
	
	public DateRange() {
		
	}
	
	public DateRange(Long id, LocalDate startDate, LocalDate endDate, double price, Long accommodationId) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.accommodationId = accommodationId;
	}
	
	public boolean IsOverlapping(DateRange other) {
		if (other.startDate.isBefore(startDate) && other.endDate.isBefore(endDate))
			return false;
		if (other.startDate.isAfter(startDate) && other.endDate.isAfter(endDate))
			return false;
		return true;
	}
	
	public boolean IsValid() {
		if (startDate.isBefore(endDate))
			return true;
		return false;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Long getAccommodationId() {
		return accommodationId;
	}
	
	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
}
