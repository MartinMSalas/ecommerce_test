package com.ecommerce.mmstechnology.ecommerce_application.service;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<UserDtoResponse> getUserById(Long id);

    public List<UserDtoResponse> getAllUsers();

    public Optional<UserDtoResponse> createUser(UserDtoRequest user);

    public Optional<UserDtoResponse> updateUser(Long id, UserDtoRequest user);

    public Optional<UserDtoResponse> deleteUser(Long id);

    public Optional<UserDtoResponse> patchUser(Long id, UserDtoRequest userDtoRequest);
}
