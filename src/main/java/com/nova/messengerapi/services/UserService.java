package com.nova.messengerapi.services;

import com.nova.messengerapi.entities.User;
import com.nova.messengerapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
}
