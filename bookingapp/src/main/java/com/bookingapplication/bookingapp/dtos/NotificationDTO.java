package com.bookingapplication.bookingapp.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bookingapplication.bookingapp.domain.NotificationType;

public class NotificationDTO {

	private Long id;
	private NotificationType type;
	private String userEmail;
	private String otherUserEmail;
	private LocalDateTime createdAt;
	
	public NotificationDTO() {
		
	}
	
	public NotificationDTO(Long id, NotificationType type, String userEmail, String otherUserEmail, LocalDateTime createdAt) {
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
    
    public void copyValues(NotificationDTO notification) {
		this.type = notification.getType();
		this.userEmail = notification.getUserEmail();
		this.otherUserEmail = notification.getOtherUserEmail();
		this.createdAt = notification.getCreatedAt();
    }
	
}
