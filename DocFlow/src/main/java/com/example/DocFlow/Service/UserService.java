package com.example.DocFlow.Service;

import com.example.DocFlow.DTOs.UserDTO;
import com.example.DocFlow.ENums.VerificationType;
import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

   @Autowired
   OrganisationRepository organisationRepository;


    public ResponseEntity addUser (User user) {
        user.setCreatedDate(new Date());
        User user1 = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());
       return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    public ResponseEntity getUser(Long userId) {
        User user1;
        try {
            user1 = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

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
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

        return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

    public ResponseEntity getAllUsers() {
        User user1;
        List<User> users;
        List<UserDTO> userDTOList = new ArrayList<>();

        try {
            users = userRepository.findAll();

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("no users", HttpStatus.NOT_FOUND);
        }

        for(User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setMobileNo(user.getMobileNo());
            userDTO.setEmail(user.getEmail());
            userDTOList.add(userDTO);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);

    }
    public ResponseEntity updateVerificationType(Long userId ,VerificationType verificationType) {
        User user;
        try {

            Optional<User> userOptional = userRepository.findById(userId);
            user = userOptional.get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (verificationType.equals(VerificationType.ALL)) {
            user.setEmailVerifiedOn(new Date());
            user.setPhoneVerifiedOn(new Date());
        } else if (verificationType.equals(VerificationType.MOBILE)) {
            user.setPhoneVerifiedOn(new Date());
        } else if (verificationType.equals(VerificationType.EMAIL)) {
            user.setEmailVerifiedOn(new Date());
        } else {
            return new ResponseEntity<>("Invalid VerificationType passed!!! " + userId, HttpStatus.OK);
        }
         userRepository.save(user);
        return new ResponseEntity<>("Verification date updated " +
                                                "successfully!!!, for user with Id " + userId, HttpStatus.OK);

    }

    public ResponseEntity  addOrgsInUser(Long userId,Long orgId){
        User user;
        Organisation organisation;
        try{
            user = userRepository.findById(userId).get();
            organisation = organisationRepository.findById(orgId).get();
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User / Oraganisation not found", HttpStatus.NOT_FOUND);

        }

        List<Organisation> organisations = user.getOrganisations();
        organisations.add(organisation);
        user.setOrganisations(organisations);
        userRepository.save(user);
        return new ResponseEntity("Organisation added to list", HttpStatus.OK);


    }

}
