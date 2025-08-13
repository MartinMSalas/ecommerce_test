package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.UserMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.repository.UserRepository;
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

    private final UserRepository userRepository;

	public UserService(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

	@Override
    public Optional<UserDtoResponse> getUserById(Integer id) {
        log.debug("Attempting to find user with ID: {}", id);
//        Optional<User> foundUser = userList.stream()
//                .filter(user -> Objects.equals(user.getUserId(), id))
//                .findFirst();

        Optional<User> foundUser = userRepository.findById(id);
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

        log.debug("Attempting to update user with ID: {}", id);
        Optional<User> foundUser = userRepository.findByUserId(id);

        if (foundUser.isEmpty()) {
            log.warn("User not found with ID: {}", id);
            return Optional.empty();
        }


        User oldUser = foundUser.get();
        userDtoRequest.setUserId(oldUser.getUserId());
        User user = userMapper.toUser(userDtoRequest);
        int linesUpdated = userRepository.updateUserById(user.getUserId(), user.getFirstName(), user.getLastName());


        if(linesUpdated > 0){
            Optional<User> userSaved = userRepository.findByUserId(user.getUserId());
            return Optional.of(userMapper.toDto(userSaved.get()));
        }
        return Optional.empty();

    }

    @Override
    public Optional<UserDtoResponse> deleteUser(Integer id) {
        log.debug("Attempting to delete user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            log.warn("User not found with ID: {}", id);
            return Optional.empty();
        }
        userRepository.deleteById(id);
        log.debug("User deleted with ID: {}", id);


        return Optional.of(userMapper.toDto(user.get()));
    }

    @Override
    public Optional<UserDtoResponse> patchUser(Integer id, UserDtoRequest userDtoRequest) {
        if (id == null) {
            log.warn("Attempted to update a user with null id");
            return Optional.empty();
        }

        log.debug("Attempting to patch user with ID: {}",id);
        Optional<User> userSaved = userRepository.findByUserId(id);

        if(userSaved.isEmpty()){
            log.warn("User not found with ID: {}", id);
            return Optional.empty();
        }
        User user = userSaved.get();
        if(hasText(userDtoRequest.getFirstName())){
            user.setFirstName(userDtoRequest.getFirstName());
        }
        if(hasText(userDtoRequest.getLastName())){
            user.setLastName(userDtoRequest.getLastName());
        }
        int linesUpdated = userRepository.updateUserById(user.getUserId(), user.getFirstName(), user.getLastName());

        if(linesUpdated > 0){

            return Optional.of(userMapper.toDto(user));
        }
        return Optional.empty();

    }

    public List<UserDtoResponse> getAllUsers(){
        log.debug("Attempting to retrieve all users");
        userList = userRepository.findAll();
        return userMapper.toDtoList(userList);
    }

    public Optional<UserDtoResponse> createUser(UserDtoRequest user){
		log.debug("Attempting to create the user: {}", user);
        if(user == null){
            log.warn("Attempting to create a null user.");
            return Optional.empty();
        }

        User userSaved = userMapper.toUser(user);
        log.info("Attempting to save the user: {}", userSaved);
        userSaved = userRepository.save(userSaved);

        log.info("User created {}", userSaved);

        return Optional.of(userMapper.toDto(userSaved));
    }

    private static boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
