package com.example.E29User.service;

import com.example.E29User.entity.User;
import com.example.E29User.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> updateUser(Long id, User user) {
        Optional<User> userId = userRepository.findById(id);
        if(userId.isPresent()) {
            user.setId(id);
            userRepository.save(user);
            return ResponseEntity.ok(userId.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
