package com.example.DocFlow.Service;

import com.example.DocFlow.DTOs.UserDTO;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public ResponseEntity addUser (User user) {
        User user1 = userRepository.save(user);
       return new ResponseEntity<>(user1, HttpStatus.OK);

    }

    public ResponseEntity getUser(Long userId){
        User user1;
        try{
           user1 = userRepository.findById(userId).get();
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user1.getUserId());
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

      return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users = userRepository.findAll();
//
//    }


    //update name with userId
    public ResponseEntity updateName(Long userId, String name){
        User user1;
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            user1 = userOptional.get();
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        user1.setName(name);
        userRepository.save(user1);

        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setUserId(user1.getUserId());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    //update name with email
    public ResponseEntity updateNameByEmail(String email,String name){
       User user1;
       try{
           Optional<User> userOptional = userRepository.findByEmail(email);
           user1 = userOptional.get();
       }
       catch(NoSuchElementException e){
           return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
       }

       user1.setName(name);
       userRepository.save(user1);

       UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setUserId(user1.getUserId());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

        return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

    public ResponseEntity getUser(String name){
       User user1;
        try{
            Optional<User>userOptional = userRepository.findByName(name);
            user1 = userOptional.get();
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user1.getUserId());
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

        return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

//    public ResponseEntity<List<User>> getAllUsers() {
//        User user1;
//        List<User> userOptional;
//        try {
//            userOptional = userRepository.findAll();
//            user1 = userOptional.get();
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<List<User>>("User not found", HttpStatus.NOT_FOUND);
//        }
//        UserDTO userDTO = new UserDTO();
//        for (int i = 0; i < userOptional.size(); i++) {
//            userDTO.setUserId(user1.getUserId());
//            userDTO.setName(user1.getName());
//            userDTO.setEmail(user1.getEmail());
//            userDTO.setMobileNo(user1.getMobileNo());
//
//        }
//        return new ResponseEntity<List<User>>((List<User>) userDTO,HttpStatus.OK);
//
//    }



}
