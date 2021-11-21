package com.rmdev.rmexpensetracker.service;

import com.rmdev.rmexpensetracker.entity.User;
import com.rmdev.rmexpensetracker.exception.RmAuthException;
import com.rmdev.rmexpensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws RmAuthException {
        if (email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws RmAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(email).matches())
            throw new RmAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw new RmAuthException("Email already in use");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}
