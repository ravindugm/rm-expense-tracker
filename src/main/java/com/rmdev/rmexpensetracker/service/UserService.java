package com.rmdev.rmexpensetracker.service;

import com.rmdev.rmexpensetracker.entity.User;
import com.rmdev.rmexpensetracker.exception.RmAuthException;

public interface UserService {

    // validate existing user
    User validateUser(String email, String password) throws RmAuthException;

    // register new user
    User registerUser(String firstName, String lastName, String email, String password) throws RmAuthException;

}
