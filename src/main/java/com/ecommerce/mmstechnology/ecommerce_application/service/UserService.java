package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.UserMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    /**
     * Centralized method to retrieve user by ID from repository
     * Handles consistent logging for all user retrieval operations
     */
    private Optional<User> getUserByIdFromRepo(Long id){
        log.debug("Attempting to find user with ID: {}", id);

        return userRepository.findById(id)
                .map(user -> {
                    log.debug("User found: {}",user);
                    return user;
                });

    }
    /**
     * Centralized method to save user to repository
     * Handles consistent logging for all user save operations
     */
    private Optional<User> saveUserToRepo(User user){
        log.debug("Saving user to database");
        User userSaved = userRepository.save(user);
        log.debug("User saved: {}",userSaved);
        return Optional.of(userSaved);
    }


    /**
     * Retrieves a user by ID
     * @param id the user ID
     * @return Optional containing UserDtoResponse if found, empty otherwise
     */
    public Optional<UserDtoResponse> getUserById(Long id) {

        return getUserByIdFromRepo(id)
                .map(userMapper::toDto);

    }

    /**
     * Updates an existing user (full update)
     * @param id the user ID
     * @param userDtoRequest the updated user data
     * @return Optional containing updated UserDtoResponse, empty if user not found or update failed
     */
    @Transactional()
    public Optional<UserDtoResponse> updateUser(Long id, UserDtoRequest userDtoRequest) {
        log.debug("Attempting to update user with ID: {}", id);

        if(getUserByIdFromRepo(id).isEmpty()){
            return Optional.empty();
        }
        userDtoRequest.setUserId(id);
        return saveUserToRepo(userMapper.toUser(userDtoRequest))
                .map(user -> {
                    log.debug("User updated {}",user);
                    return userMapper.toDto(user);
                });

    }


    /**
     * Deletes a user by ID
     * @param id the user ID
     * @return Optional containing deleted UserDtoResponse, empty if user not found
     */
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


    /**
     * Partially updates an existing user
     * @param id the user ID
     * @param userDtoRequest the partial user data
     * @return Optional containing updated UserDtoResponse, empty if user not found or update failed
     */
    @Transactional
    public Optional<UserDtoResponse> patchUser(Long id, UserDtoRequest userDtoRequest) {

        log.debug("Attempting to patch user with ID: {}",id);


        Optional<User> user = getUserByIdFromRepo(id);
        if(user.isEmpty()){
            return Optional.empty();
        }
        User userToUpdate = user.get();

        if(hasText(userDtoRequest.getFirstName())){
            userToUpdate.setFirstName(userDtoRequest.getFirstName());
        }
        if(hasText(userDtoRequest.getLastName())){
            userToUpdate.setLastName(userDtoRequest.getLastName());
        }
        return saveUserToRepo(userToUpdate)
                .map(userPatched -> {
                    log.debug("User patched {}",userPatched);
                    return userMapper.toDto(userPatched);
                });
    }
    /**
     * Retrieves all users
     * @return List of UserDtoResponse (never null, but may be empty)
     */
    public List<UserDtoResponse> getAllUsers(){
        log.debug("Attempting to retrieve all users");

        return userMapper.toDtoList(userRepository.findAll());
    }
    /**
     * Creates a new user
     * @param userDtoRequest the user data
     * @return Optional containing created UserDtoResponse, empty if creation failed
     */
    public Optional<UserDtoResponse> createUser(UserDtoRequest userDtoRequest){
		log.debug("Attempting to create the user: {}", userDtoRequest);

        Optional<User> userSaved = saveUserToRepo(userMapper.toUser(userDtoRequest));
        if(userSaved.isEmpty()){
            return Optional.empty();
        }
        log.info("User created {}", userSaved.get());
        return Optional.of(userMapper.toDto(userSaved.get()));

    }

    private static boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
