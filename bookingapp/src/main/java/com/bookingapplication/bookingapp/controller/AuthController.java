package com.bookingapplication.bookingapp.controller;

import java.net.URI;
import java.nio.CharBuffer;
import java.time.format.SignStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapplication.bookingapp.config.UserAuthenticationProvider;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.SignUpDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;
import com.bookingapplication.bookingapp.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO) {
		UserDTO userDTO = userService.login(credentialsDTO);
		userDTO.setToken(userAuthenticationProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid SignUpDTO user) {
        UserDTO createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getEmail())).body(createdUser);
    }
	
	@PutMapping(value = "/account/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(@RequestBody SignUpDTO signUpDTO, @PathVariable String email)
			throws Exception {
		UserDTO updatedUser = userService.update(signUpDTO, email);
		updatedUser.setToken(userAuthenticationProvider.createToken(updatedUser));
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping(value = "/account/{email}")
	public ResponseEntity<UserDTO> delete(@PathVariable("email") String email) {
		userService.delete(email);
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

}
