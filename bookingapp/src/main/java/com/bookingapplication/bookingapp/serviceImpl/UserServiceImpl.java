package com.bookingapplication.bookingapp.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.RegistrationDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repository.UserRepository;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl {
	
	private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
	
    public UserDTO login(CredentialsDTO credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
        	
        	// OVo  return userMapper.toUserDto(user);
            return new UserDTO();
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
    
    public UserDTO register(RegistrationDTO userDTO) {
    	
    	Optional<User> optionalUser = userRepository.findByLogin(userDTO.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        /* isto valjda?
        User findUser = userRepository.findOne(userDTO.email());
        if (findUser != null) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }*/

        //User user = userMapper.signUpToUser(userDTO);
        //user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.password())));
        //User savedUser = userRepository.save(user);

        //return userMapper.toUserDTO(savedUser);
    }
    
	public UserDTO findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        
        // OVo  return userMapper.toUserDto(user);
        return new UserDTO();
    }

}
