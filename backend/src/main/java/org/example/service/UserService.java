package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, Long, UserRepository> {

    public Optional<User> findUserByUsername(String username) {
        return r.findByUsername(username);
    }
}
