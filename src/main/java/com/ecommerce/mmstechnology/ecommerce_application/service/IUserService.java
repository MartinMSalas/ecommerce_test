package com.ecommerce.mmstechnology.ecommerce_application.service;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<UserDtoResponse> getUserById(Integer id);

    public List<UserDtoResponse> getAllUsers();

    public Optional<UserDtoResponse> createUser(UserDtoRequest user);

}
