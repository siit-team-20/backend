package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.Notification;

public interface NotificationService {

	Collection<Notification> findAll();

	Notification findOne(Long id);

	Notification create(Notification notification) throws Exception;

	Notification update(Notification notification) throws Exception;

	void delete(Long id);
	
}
