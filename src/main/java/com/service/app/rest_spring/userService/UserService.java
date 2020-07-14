package com.service.app.rest_spring.userService;

import com.service.app.rest_spring.dto.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    Map<String, User> users;



    @Override
    public Map<String, User> getUsers() {
        return users;
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public User createUser(User user) {
        User response = new User();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserId(UUID.randomUUID().toString());

        if (users == null)
            users = new HashMap<>();
        users.put(response.getUserId(), response);
        return user;
    }

    @Override
    public User updateUser(User user, String userId) {
        User actual = users.get(userId);
        if (user.getFirstName() != null)
            actual.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            actual.setLastName(user.getLastName());
        if (user.getEmail() != null)
            actual.setEmail(user.getEmail());
        users.replace(userId, actual);
        return actual;
    }

    @Override
    public void deleteUser(String userId) {
        users.remove(userId);
    }
}
