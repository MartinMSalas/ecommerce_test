package com.ecommerce.mmstechnology.ecommerce_application.service;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<User> getUserById(int id);

    public List<User> getAllUsers();

    public Optional<User> createUser(User user);

}
