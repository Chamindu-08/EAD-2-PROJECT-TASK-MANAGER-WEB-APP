package com.nibm.taskTinker.service;

import com.nibm.taskTinker.Exception.ResourceNotFoundException;
import com.nibm.taskTinker.model.User;
import com.nibm.taskTinker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    public User addUser(User newUser){
        return userRepository.save(newUser);
    }

    public User updateUser(String email, User updateUser) {
        Optional<User> existingUserOpt = userRepository.findById(email);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            // Update fields with the data from updateUser
            existingUser.setUserName(updateUser.getUserName());
            existingUser.setUserPosition(updateUser.getUserPosition());
            existingUser.setUserPassword(updateUser.getUserPassword());
            return userRepository.save(existingUser); // Save and return the updated user
        } else {
            // Handle the case where the user does not exist
            throw new ResourceNotFoundException("User with email " + email + " not found.");
        }
    }


}
