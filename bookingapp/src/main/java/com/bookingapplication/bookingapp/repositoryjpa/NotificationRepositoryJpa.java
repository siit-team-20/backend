package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.Notification;

public interface NotificationRepositoryJpa extends JpaRepository<Notification, Long> {

}
