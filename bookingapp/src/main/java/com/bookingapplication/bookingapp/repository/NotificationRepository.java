package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Notification;

public interface NotificationRepository {

	Collection<Notification> findAll();

	Notification create(Notification notification);

	Notification findOne(Long id);
	
	Notification update(Notification notification);

	void delete(Long id);
	
}
