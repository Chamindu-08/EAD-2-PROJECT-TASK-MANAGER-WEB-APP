package com.nibm.taskTinker.controller;

import com.nibm.taskTinker.Exception.ResourceNotFoundException;
import com.nibm.taskTinker.model.User;
import com.nibm.taskTinker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail) {
        try {
            User user = userService.getUserByEmail(userEmail);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(404).body("User with email " + userEmail + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User updateUser) {
        try {
            User updatedUser = userService.updateUser(email, updateUser);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
