package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.UserMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
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

    private final UserMapper userMapper;
    private final UserRepository userRepository;

	public UserService(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    private Optional<User> getUserByIdFromRepo(Long id){
        log.debug("Attempting to find user with ID: {}", id);

        return userRepository.findById(id)
                .map(user -> {
                    log.debug("User found: {}",user);
                    return user;
                });

    }

    private Optional<User> saveUserToRepo(User user){
        log.debug("Saving user to database");
        User userSaved = userRepository.save(user);
        log.debug("User saved: {}",userSaved);
        return Optional.of(userSaved);
    }


    @Transactional(readOnly = true)
    public Optional<UserDtoResponse> getUserById(Long id) {

        return getUserByIdFromRepo(id)
                .map(userMapper::toDto);

    }
    @Transactional()
    public Optional<UserDtoResponse> updateUser(Long id, UserDtoRequest userDtoRequest) {
        log.debug("Attempting to update user with ID: {}", id);
        return getUserByIdFromRepo(id)
                .map(user -> {
                    userDtoRequest.setUserId(id);
                    saveUserToRepo(userMapper.toUser(userDtoRequest))
                            .map({ user2 ->
                                    return userMapper.toDto(user2);

                    });
                    return null;
                });


    }


    @Transactional()
    public Optional<UserDtoResponse> deleteUser(Long id) {
        log.debug("Attempting to delete user with ID: {}", id);

        return getUserByIdFromRepo(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    log.debug("User with id {} deleted", id);
                    return userMapper.toDto(user);
                });
    }



    @Transactional
    public Optional<UserDtoResponse> patchUser(Long id, UserDtoRequest userDtoRequest) {

       log.debug("Attempting to patch user with ID: {}",id);

       return getUserByIdFromRepo(id)
               .map(user -> {
                  if(userDtoRequest.getFirstName() != null){
                      user.setFirstName(userDtoRequest.getFirstName());
                  }
                  if(userDtoRequest.getLastName() != null){
                      user.setLastName(userDtoRequest.getLastName());
                  }
                   User userSaved = userRepository.save(userMapper.toUser(userDtoRequest));
                   log.debug("User saved: {}",userSaved);
                   return userMapper.toDto(userSaved);
               });
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
