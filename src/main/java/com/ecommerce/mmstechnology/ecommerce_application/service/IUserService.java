package com.ecommerce.mmstechnology.ecommerce_application.service;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<UserResponseDto> getUserById(Long id);

    public List<UserResponseDto> getAllUsers();

    public Optional<UserResponseDto> createUser(UserRequestDto user);

    public Optional<UserResponseDto> updateUser(Long id, UserRequestDto user);

    public Optional<UserResponseDto> deleteUser(Long id);

    public Optional<UserResponseDto> patchUser(Long id, UserRequestDto userRequestDto);
}
