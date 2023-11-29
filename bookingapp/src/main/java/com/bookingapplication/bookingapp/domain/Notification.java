package com.bookingapplication.bookingapp.domain;

public class Notification {

	private Long id;
	private NotificationType type;
	private String userEmail;
	
	public Notification() {
		
	}
	
	public Notification(Long id, NotificationType type, String userEmail) {
		this.id = id;
		this.type = type;
		this.userEmail = userEmail;
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
    
    public void copyValues(Notification notification) {
		this.type = notification.getType();
		this.userEmail = notification.getUserEmail();
    }
	
}
