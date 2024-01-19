package com.bookingapplication.bookingapp.service;

import java.util.List;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.SignUpDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;

public interface UserService {
	
	UserDTO login(CredentialsDTO credentialsDTO);

	UserDTO register(SignUpDTO signUpDTO);
	
	UserDTO update(SignUpDTO signUpDTO, String email) throws Exception;
	
	void delete(String email);

	UserDTO findByEmail(String email);
	
	User toUser(UserDTO userDTO);

	UserDTO toUserDTO(User user);

	List<UserDTO> toUserDTOs(List<User> user);

	void updateUser(User target, User source);
	
	User signUpToUser(SignUpDTO signUpDTO);
}
