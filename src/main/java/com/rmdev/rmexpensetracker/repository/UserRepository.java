package com.rmdev.rmexpensetracker.repository;

import com.rmdev.rmexpensetracker.entity.User;
import com.rmdev.rmexpensetracker.exception.RmAuthException;

public interface UserRepository {

    // create a new user
    Integer create(String firstName, String lastName, String email, String password) throws RmAuthException;

    // find by email and password
    User findByEmailAndPassword(String email, String password) throws RmAuthException;

    // check user availability in the database
    Integer getCountByEmail(String email);

    // find user by given user id
    User findById(Integer userId);

}
