package com.marziehvahid.springbootmongodb.service;

import com.marziehvahid.springbootmongodb.exception.RecordNotFoundException;
import com.marziehvahid.springbootmongodb.model.User;
import com.marziehvahid.springbootmongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(BigInteger userId) {
        return userRepository.findById(userId)
                .orElseThrow(RecordNotFoundException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Object getAllUserAddresses(BigInteger userId) {
        User user = findOne(userId);
        if (user != null) {
            return user.getAddresses();
        } else {
            return "User not found.";
        }
    }


    public User update(User user, BigInteger userId) {
        User oldUser = findOne(userId);
        BeanUtils.copyProperties(oldUser, user);
        user.setId(userId);
        return userRepository.save(user);
    }

    public void delete(BigInteger userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RecordNotFoundException("User not found");
        }
    }
}
