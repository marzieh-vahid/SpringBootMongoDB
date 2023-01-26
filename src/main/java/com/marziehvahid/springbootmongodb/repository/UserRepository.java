package com.marziehvahid.springbootmongodb.repository;

import com.marziehvahid.springbootmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */
@Repository
public interface UserRepository extends MongoRepository<User, BigInteger> {
}
