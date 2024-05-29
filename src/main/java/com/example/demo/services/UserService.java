package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.helper.ExceptionHelper;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    creating user
    public User createUser(User user) {
        return userRepository.save(user);
    }

//    getting all user
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
//    getting single user
    public User getUserById(int userId) {
//       User user =  userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found "));
        User user = userRepository.findById(userId).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
       return user;

    }
//    updating user
      public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
      }
//    deleting user
    public boolean deleteUser(int userID) {
        User user = userRepository.findById(userID).orElseThrow(ExceptionHelper::throwResourceNotFoundException);
        userRepository.delete(user);
        return true;
    }




}
