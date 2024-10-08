package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private NotificationType type;
	@Column
	private String userEmail;
	@Column
	private String otherUserEmail;
	@Column
	private LocalDateTime createdAt;
	
	public Notification() {
		
	}
	
	public Notification(Long id, NotificationType type, String userEmail, String otherUserEmail, LocalDateTime createdAt) {
		this.id = id;
		this.type = type;
		this.userEmail = userEmail;
		this.otherUserEmail = otherUserEmail;
		this.createdAt = createdAt;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
    
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getOtherUserEmail() {
        return otherUserEmail;
    }

    public void setOtherUserEmail(String otherUserEmail) {
        this.otherUserEmail = otherUserEmail;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void copyValues(Notification notification) {
		this.type = notification.getType();
		this.userEmail = notification.getUserEmail();
		this.otherUserEmail = notification.getOtherUserEmail();
		this.createdAt = notification.getCreatedAt();
    }
	
}
