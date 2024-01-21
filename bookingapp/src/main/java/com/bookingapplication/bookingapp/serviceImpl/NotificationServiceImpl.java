package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.Notification;
import com.bookingapplication.bookingapp.dtos.NotificationDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.NotificationRepositoryJpa;
import com.bookingapplication.bookingapp.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepositoryJpa notificationRepositoryJpa;

	@Override
	public Collection<NotificationDTO> findAll() {
		return toNotificationDTOs(notificationRepositoryJpa.findAll());
	}
	
	@Override
	public Collection<NotificationDTO> findAll(String userEmail) {
		return toNotificationDTOs(notificationRepositoryJpa.findAll().stream().filter(a -> a.getUserEmail().equals(userEmail)).collect(Collectors.toList()));
	}


	@Override
	public NotificationDTO findOne(Long id) {
		Notification notification = notificationRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Notification not found", HttpStatus.NOT_FOUND));
        return toNotificationDTO(notification);
	}

	@Override
	public NotificationDTO create(NotificationDTO notificationDTO) throws Exception {
		Notification notification = toNotification(notificationDTO);
		if (notification.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Notification savedNotification = notificationRepositoryJpa.save(notification);
		return toNotificationDTO(savedNotification);
	}

	@Override
	public NotificationDTO update(NotificationDTO notificationDTO, Long id) throws Exception {

		Notification notification = notificationRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Notification not found", HttpStatus.NOT_FOUND));

		updateNotification(notification, toNotification(notificationDTO));

		Notification savedNotification = notificationRepositoryJpa.save(notification);

        return toNotificationDTO(savedNotification);
	}

	@Override
	public void delete(Long id) {
		notificationRepositoryJpa.deleteById(id);
	}

    @Override
    public void updateNotification(Notification target, Notification source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setUserEmail( source.getUserEmail() );
        target.setType(source.getType());
        target.setOtherUserEmail(source.getOtherUserEmail());
        target.setCreatedAt(source.getCreatedAt());
    }

	@Override
	public Notification toNotification(NotificationDTO notificationDTO) {
		if ( notificationDTO == null ) {
            return null;
        }

		Notification notification = new Notification();

		notification.setId( notificationDTO.getId() );
		notification.setUserEmail( notificationDTO.getUserEmail() );
		notification.setType( notificationDTO.getType() );
		notification.setOtherUserEmail( notificationDTO.getOtherUserEmail() );
		notification.setCreatedAt( notificationDTO.getCreatedAt() );
        
        return notification;
	}

	@Override
	public NotificationDTO toNotificationDTO(Notification notification) {
		if ( notification == null ) {
            return null;
        }

		NotificationDTO notificationDTO = new NotificationDTO();

		notificationDTO.setId( notification.getId() );
		notificationDTO.setUserEmail( notification.getUserEmail() );
		notificationDTO.setType(notification.getType());
		notificationDTO.setOtherUserEmail(notification.getOtherUserEmail());
		notificationDTO.setCreatedAt(notification.getCreatedAt());
        
        return notificationDTO;
	}

	@Override
	public List<NotificationDTO> toNotificationDTOs(List<Notification> notifications) {
		if ( notifications == null ) {
            return null;
        }

        List<NotificationDTO> list = new ArrayList<NotificationDTO>( notifications.size() );
        for ( Notification notification : notifications ) {
            list.add( toNotificationDTO( notification ) );
        }

        return list;
	}

}
