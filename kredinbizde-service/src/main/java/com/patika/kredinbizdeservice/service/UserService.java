package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.dto.response.UserDTO;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Address;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final NotificationProducer notificationProducer;

    //create new user
    public User save(User user) {
        if (!user.getPassword().equals(user.getPassword2())) {
        	throw new KredinbizdeException(ExceptionMessages.PASSWORD_NOT_MATCH);
        }
        User foundUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (foundUser != null) {
        	throw new KredinbizdeException(ExceptionMessages.EMAIL_TAKEN);
        }        
        user.setIsActive(true);
        User savedUser = userRepository.save(user);
        //notificationProducer.sendNotification(prepareNotificationDTO(NotificationType.EMAIL, user.getEmail()));
        return savedUser;
    }

    //prepare notification
    private NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi.")
                .notificationType(notificationType)
                .email(email)
                .build();
    }

    //return all users
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertUsertoDTO).toList();
    }

    //find user by email
    public UserDTO getByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);   
        User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));
        UserDTO userDto = convertUsertoDTO(user);
    	return userDto;
    }

    //find user by id
    public UserDTO getById(Long userId) {
    	Optional<User> foundUser = userRepository.findById(userId);
    	User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));
    	UserDTO userDto = convertUsertoDTO(user);
    	return userDto;
    }
    
    public User getUserById(Long userId) {
    	Optional<User> foundUser = userRepository.findById(userId);
    	User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));   	
    	return user;
    }
    
    //convert user to user dto
	private UserDTO convertUsertoDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.name(user.getName())
				.surname(user.getSurname())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.birthDate(user.getBirthDate())
				.address(user.getAddress()).build();
	}
}
