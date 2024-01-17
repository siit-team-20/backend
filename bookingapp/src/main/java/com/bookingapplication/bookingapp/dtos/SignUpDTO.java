package com.bookingapplication.bookingapp.dtos;

import com.bookingapplication.bookingapp.domain.UserType;

public record SignUpDTO (String email, char[] password, String name, String surname, String address, String phone, UserType type) { }
