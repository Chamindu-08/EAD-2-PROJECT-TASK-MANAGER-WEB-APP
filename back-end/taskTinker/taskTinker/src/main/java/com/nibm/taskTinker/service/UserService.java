package com.nibm.taskTinker.service;

import com.nibm.taskTinker.Exception.ResourceNotFoundException;
import com.nibm.taskTinker.dto.UserDTO;
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

    //get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by email
    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    //login
    public User loginUser(String email, String password) {
        User user = userRepository.findByUserEmail(email);
        if (user != null) {
            if (user.getUserPassword().equals(password)) {
                return user;
            } else {
                throw new ResourceNotFoundException("Incorrect password.");
            }
        } else {
            throw new ResourceNotFoundException("User with email " + email + " not found.");
        }
    }

    //register
    public User registerUser(User user) {
        //check if the user already exists
        if (userRepository.existsById(user.getUserEmail())) {
            throw new ResourceNotFoundException("User with email " + user.getUserEmail() + " already exists.");
        }

        //check if the user name is null
        if (user.getUserName() == null) {
            throw new ResourceNotFoundException("User name cannot be null.");
        }

        //check if the user position is null
        if (user.getUserPosition() == null) {
            throw new ResourceNotFoundException("User position cannot be null.");
        }

        //check if the user password and confirm password is null
        if (user.getUserPassword() == null || user.getConfirmPassword() == null) {
            throw new ResourceNotFoundException("User password and confirm password cannot be null.");
        }

        //check if the user password and confirm password match
        if (!user.getUserPassword().equals(user.getConfirmPassword())) {
            throw new ResourceNotFoundException("User password and confirm password do not match.");
        }

        //save the user
        return userRepository.save(user);
    }

    public User updateUser(String email, User updateUser) {
        Optional<User> existingUserOpt = userRepository.findById(email);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            //check if the user name is null
            if (updateUser.getUserName() != null) {
                existingUser.setUserName(updateUser.getUserName());
            }

            //check if the user position is null
            if (updateUser.getUserPosition() != null) {
                existingUser.setUserPosition(updateUser.getUserPosition());
            }

            //check if the user password and confirm password is null
            if (updateUser.getUserPassword() != null && updateUser.getConfirmPassword() != null) {
                //check if the user password and confirm password match
                if (updateUser.getUserPassword().equals(updateUser.getConfirmPassword())) {
                    existingUser.setUserPassword(updateUser.getUserPassword());
                } else {
                    throw new ResourceNotFoundException("User password and confirm password do not match.");
                }
            }

            //save the updated user
            return userRepository.save(existingUser);
        } else {
            //handle the case where the user is not found
            throw new ResourceNotFoundException("User with email " + email + " not found.");
        }
    }


}
