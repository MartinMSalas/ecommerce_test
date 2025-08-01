package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService implements IUserService {

    List<User> userList = new ArrayList<>();

    @Override
    public Optional<User> getUserById(int id) {
        return userList.stream()
                .filter(user -> Objects.equals(user.getUserId(), id))
                .findFirst();

    }

    public List<User> getAllUsers(){
        return userList;
    }


    public Optional<User> createUser(User user){

        int id = userList.size() + 1;
        user.setUserId(id);
        userList.add(user);
        System.out.println("This is the user " + userList.get(id-1));

        return user;
    }
}
