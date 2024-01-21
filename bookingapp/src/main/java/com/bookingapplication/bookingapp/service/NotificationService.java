package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.Notification;
import com.bookingapplication.bookingapp.dtos.NotificationDTO;

public interface NotificationService {

	Collection<NotificationDTO> findAll();

	Collection<NotificationDTO> findAll(String userEmail);

	NotificationDTO findOne(Long id);

	NotificationDTO create(NotificationDTO notification) throws Exception;

	NotificationDTO update(NotificationDTO notification, Long id) throws Exception;

	void delete(Long id);
	
	Notification toNotification(NotificationDTO notificationDTO);

	NotificationDTO toNotificationDTO(Notification notification);

    List<NotificationDTO> toNotificationDTOs(List<Notification> notifications);

    void updateNotification(Notification target, Notification source);
	
}
