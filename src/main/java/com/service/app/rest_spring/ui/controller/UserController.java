package com.service.app.rest_spring.ui.controller;

import com.service.app.rest_spring.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") //  http://localhost:8080/users
public class UserController {

    Map<String, User> users;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUsers(@RequestParam(required = false) String page,
                                   @RequestParam(required = false) String elements){
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{user-id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUser(@PathVariable(value = "user-id") String userId){
        return new ResponseEntity(users.get(userId), HttpStatus.OK);
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User response = new User();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserId(UUID.randomUUID().toString());

        if (users == null)
            users = new HashMap<>();
        users.put(response.getUserId(), response);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{user-id}",
                consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateUser(@PathVariable(value = "user-id") String userId,
                                     @RequestBody User user){
        User actual = users.get(userId);
        if (user.getFirstName() != null)
            actual.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            actual.setLastName(user.getLastName());
        if (user.getEmail() != null)
            actual.setEmail(user.getEmail());
        users.replace(userId, actual);
        return new ResponseEntity(actual, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{user-id}")
    public ResponseEntity deleteUser(@PathVariable(value = "user-id") String userId){
        users.remove(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
