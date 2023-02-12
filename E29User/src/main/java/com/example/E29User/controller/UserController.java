package com.example.E29User.controller;

import com.example.E29User.entity.User;
import com.example.E29User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

      private UserService userService;

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(path = "/viewall")
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        //user.setId(id);
        return userService.updateUser(id, user);
    }

}
