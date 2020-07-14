package com.service.app.rest_spring.ui.controller;

import com.service.app.rest_spring.dto.User;
import com.service.app.rest_spring.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IUserService iUserService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUsers(@RequestParam(required = false) String page,
                                   @RequestParam(required = false) String elements){
        Map<String, User> users = iUserService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{user-id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUser(@PathVariable(value = "user-id") String userId){
        User user = iUserService.getUser(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User response = iUserService.createUser(user);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{user-id}",
                consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateUser(@PathVariable(value = "user-id") String userId,
                                     @RequestBody User user){
        User actual = iUserService.updateUser(user, userId);
        return new ResponseEntity(actual, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{user-id}")
    public ResponseEntity deleteUser(@PathVariable(value = "user-id") String userId){
        iUserService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
