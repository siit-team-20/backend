package com.bookingapplication.bookingapp.dtos;

import java.time.LocalDate;


public class DateRangeDTO {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private double price;
	private Long accommodationId;
	
	public DateRangeDTO() {
		
	}
	
	public DateRangeDTO(Long id, LocalDate startDate, LocalDate endDate, double price, Long accommodationId) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.accommodationId = accommodationId;
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
