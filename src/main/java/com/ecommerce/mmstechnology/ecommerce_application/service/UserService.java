package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.UserMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service
public class UserService implements IUserService {

    List<User> userList = new ArrayList<>();
    private final UserMapper userMapper;

	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
    public Optional<UserDtoResponse> getUserById(Integer id) {
        log.debug("Attempting to find user with ID: {}", id);
        Optional<User> foundUser = userList.stream()
                .filter(user -> Objects.equals(user.getUserId(), id))
                .findFirst();


        if (foundUser.isPresent()) {
            log.debug("User found: {}", foundUser.get());
            return Optional.of(userMapper.toDto(foundUser.get()));
        } else {
            log.warn("User not found with ID: {}", id);
            return Optional.empty();
        }



    }

    public Optional<UserDtoResponse> updateUser(Integer id, UserDtoRequest userDtoRequest){

        if (id == null) {
            log.warn("Attempted to update a user with null id");
            return Optional.empty();
        }
        // Find index by id (null-safe comparison)
        int index = IntStream.range(0, userList.size())
                .filter(i -> Objects.equals(userList.get(i).getUserId(), id))
                .findFirst()
                .orElse(-1);

        if(index != -1){
            log.debug("User found with ID: {}", id);
            User user = userList.get(index);
            if( hasText(userDtoRequest.getFirstName())){
                user.setFirstName(userDtoRequest.getFirstName());
            }
            if( hasText(userDtoRequest.getLastName())){
                user.setLastName(userDtoRequest.getLastName());
            }
            userList.set(index,user);
            return Optional.of(userMapper.toDto(user));
        } else {
            return Optional.empty();
        }

    }

    public List<UserDtoResponse> getAllUsers(){
        log.debug("Attempting to retrieve all users");
        return userMapper.toDtoList(userList);

    }


    public Optional<UserDtoResponse> createUser(UserDtoRequest user){
		log.debug("Attempting to create the user: {}", user);
        if(user == null){
            log.warn("Attempting to create a null user.");
            return Optional.empty();
        }

        int id = userList.size() + 1;
        user.setUserId(id);

        User userSaved = userMapper.toUser(user);
        userList.add(userSaved);

        log.info("User created {}", userSaved);


        return Optional.of(userMapper.toDto(userSaved));
    }

    private static boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
