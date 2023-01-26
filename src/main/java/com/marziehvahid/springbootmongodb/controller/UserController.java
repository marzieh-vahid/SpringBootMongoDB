package com.marziehvahid.springbootmongodb.controller;

import com.marziehvahid.springbootmongodb.model.User;
import com.marziehvahid.springbootmongodb.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable BigInteger userId) {
        LOG.info("Getting user with ID: {}.", userId);
        return userService.findOne(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        return userService.save(user);
    }

    @RequestMapping(value = "/addresses/{userId}", method = RequestMethod.GET)
    public Object getAllUserAddresses(@PathVariable BigInteger userId) {
        return userService.getAllUserAddresses(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, @PathVariable BigInteger userId) {
        return userService.update(user, userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable BigInteger userId) {
        userService.delete(userId);
    }


}
