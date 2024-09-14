package com.nibm.taskTinker.controller;

import com.nibm.taskTinker.Exception.ResourceNotFoundException;
import com.nibm.taskTinker.dto.UserDTO;
import com.nibm.taskTinker.mapper.UserMapper;
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

    //get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        try {
            List<User> users = userService.getAllUsers();

            //copy the user objects to new dto objects
            List<UserDTO> userDTOs = UserMapper.toDTOList(users);

            return ResponseEntity.ok(userDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    //get user by email
    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail) {
        try {
            User user = userService.getUserByEmail(userEmail);
            if (user != null) {
                //copy the user object to a new dto object
                UserDTO userDTO = UserMapper.toDTO(user);

                return ResponseEntity.ok(userDTO);
            } else {
                return ResponseEntity.status(404).body("User with email " + userEmail + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal error occurred: " + e.getMessage());
        }
    }

    //login
    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.loginUser(email, password);

            //copy the user object to a new dto object
            UserDTO userDTO = UserMapper.toDTO(user);

            return ResponseEntity.ok(userDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User newUser) {
        try {
            User user = userService.registerUser(newUser);

            //copy the user object to a new dto object
            UserDTO userDTO = UserMapper.toDTO(user);

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestBody User updateUser) {
        try {
            User updatedUser = userService.updateUser(email, updateUser);

            //copy the user object to a new dto object
            UserDTO userDTO = UserMapper.toDTO(updatedUser);

            return ResponseEntity.ok(userDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
