package com.bookingapplication.bookingapp.domain;

import com.bookingapplication.bookingapp.dtos.AccommodationRequestType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class AccommodationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    @Column
	private Long oldAccommodationId;
    @Column
    private Long newAccommodationId;
    @Column
    private AccommodationRequestType accommodationRequestType;

	public AccommodationRequest() {

	}

	public AccommodationRequest(Long id, Long oldAccommodationId, Long newAccommodationId, AccommodationRequestType accommodationRequestType) {
		super();
		this.id = id;
		this.oldAccommodationId = oldAccommodationId;
		this.newAccommodationId = newAccommodationId;
		this.accommodationRequestType = accommodationRequestType;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOldAccommodationId() {
		return oldAccommodationId;
	}
	
	public void setOldAccommodationId(Long oldAccommodationId) {
		this.oldAccommodationId = oldAccommodationId;
	}
	
	public Long getNewAccommodationId() {
		return newAccommodationId;
	}
	
	public void setNewAccommodationId(Long newAccommodationId) {
		this.newAccommodationId = newAccommodationId;
	}
	
	public AccommodationRequestType getAccommodationRequestType() {
		return accommodationRequestType;
	}
	
	public void setAccommodationRequestType(AccommodationRequestType accommodationRequestType) {
		this.accommodationRequestType = accommodationRequestType;
	}
	
	public void copyValues(AccommodationRequest accommodationRequest) {
		this.oldAccommodationId = accommodationRequest.oldAccommodationId;
		this.newAccommodationId = accommodationRequest.newAccommodationId;
		this.accommodationRequestType = accommodationRequest.accommodationRequestType;
	}
	
}
