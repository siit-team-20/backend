package com.bookingapplication.bookingapp.serviceImpl;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.CredentialsDTO;
import com.bookingapplication.bookingapp.dtos.SignUpDTO;
import com.bookingapplication.bookingapp.dtos.UserDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.UserRepositoryJpa;
import com.bookingapplication.bookingapp.service.UserService;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepositoryJpa userRepositoryJpa;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
    public UserDTO login(CredentialsDTO credentialsDto) {
        User user = userRepositoryJpa.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return toUserDTO(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
    
	@Override
    public UserDTO register(SignUpDTO userDTO) {
    	
    	Optional<User> optionalUser = userRepositoryJpa.findByEmail(userDTO.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }
        
        User user = signUpToUser(userDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.password())));

        User savedUser = userRepositoryJpa.save(user);

        return toUserDTO(savedUser);
    }
	
	@Override
	public UserDTO update(SignUpDTO signUpDTO, String email) throws Exception {
		User user = userRepositoryJpa.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
		
		User updatedUser = new User(signUpDTO.email(), "", signUpDTO.name(), signUpDTO.surname(), signUpDTO.address(), signUpDTO.phone(), signUpDTO.type());

		if (signUpDTO.password().length == 0) {
			updatedUser.setPassword(user.getPassword());
		}
		else {
			updatedUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));
		}

		delete(email);

        User savedUser = userRepositoryJpa.save(updatedUser);

        return toUserDTO(savedUser);
	}
	
	@Override
	public void delete(String email) {
		userRepositoryJpa.deleteById(email);
	}
    
	@Override
	public UserDTO findByEmail(String email) {
        User user = userRepositoryJpa.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        
        return toUserDTO(user);
    }
	
	@Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userDTO.getEmail() );
    	user.setName( userDTO.getName());
    	user.setPhone(userDTO.getPhone());
    	user.setSurname(userDTO.getSurname());
    	user.setType(userDTO.getType());
    	user.setAddress(userDTO.getAddress());

        return user;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;

        email = user.getEmail();
        String name = user.getName();
        String phone = user.getPhone();
        String surname = user.getSurname();
        UserType type = user.getType();
        String address = user.getAddress();
        String password = user.getPassword();

        UserDTO userDTO = new UserDTO(email, password, name, surname, address, phone, type);

        return userDTO;
    }

    @Override
    public List<UserDTO> toUserDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( toUserDTO( user ) );
        }

        return list;
    }

    @Override
    public void updateUser(User target, User source) {
        if ( source == null ) {
            return;
        }

        target.setAddress( source.getAddress() );
        target.setEmail( source.getEmail() );
        target.setName( source.getName() );
        target.setPassword( source.getPassword());
        target.setPhone( source.getPhone() );
        target.setSurname( source.getSurname() );
        target.setType( source.getType());
    }
    
    @Override 
    public User signUpToUser(SignUpDTO signUpDTO) {
    	if ( signUpDTO == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( signUpDTO.email() );
    	user.setName( signUpDTO.name());
    	user.setPhone(signUpDTO.phone());
    	user.setSurname(signUpDTO.surname());
    	user.setType(signUpDTO.type());
    	user.setAddress(signUpDTO.address());

        return user;
    }

}
