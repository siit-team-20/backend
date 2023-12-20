package com.bookingapplication.bookingapp.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.RegistrationDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;
import com.bookingapplication.bookingapp.service.UserService;

@RestController
public class AuthController {
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO){
		
		UserDTO user = userService.login(credentialsDTO);
		return ResponseEntity.ok(user);
		
	}
	
	@PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegistrationDTO user) {
        UserDTO createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        //                                                           nama getuje email
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
