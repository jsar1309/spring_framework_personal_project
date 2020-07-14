package com.service.app.rest_spring.userService;

import com.service.app.rest_spring.dto.User;

import java.util.Map;

public interface IUserService {

    Map<String, User> getUsers ();

    User getUser(String userId);

    User createUser(User user);

    User updateUser(User user, String userId);

    void deleteUser(String userId);
}
