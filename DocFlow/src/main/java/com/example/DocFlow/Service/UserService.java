package com.example.DocFlow.Service;

import com.example.DocFlow.DTOs.UserDTO;
import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Enums.VerificationType;
import com.example.DocFlow.Exceptions.DatabaseConnectionException;
import com.example.DocFlow.Exceptions.DuplicateKeyException;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Repository.UserRepository;
import com.example.DocFlow.Utils.Validator;
//import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.*;
import java.util.zip.DataFormatException;

import static com.example.DocFlow.DocFlowApplication.logger;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganisationRepository organisationRepository;

    public ResponseEntity addUser (User user) throws Exception {

        if (!Validator.validateEmail(user.getEmail()))
            throw new DataFormatException("Email is not valid!!!");

        if (!Validator.validatePhone(user.getMobileNo()))
            throw new DataFormatException("Invalid Mobile Number!!!");
        user.setCreatedDate(new Date());

        if (!Validator.validateName(user.getName()))
            throw new DataFormatException("Invalid name!");

        User savedUser;

        try {
            savedUser = userRepository.save(user);
            logger.trace("user added!!");
        } catch (DataIntegrityViolationException e) {
            logger.error(e.toString());
            throw new DuplicateKeyException("One of name, phone, email already exists in our Database!!");
        } catch (CannotCreateTransactionException e) {
            logger.error(e.toString());
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e + " ");
        }
        logger.info("no excpetion");
        return new ResponseEntity<>("User Added Successfully!", HttpStatus.OK);

    }



      public ResponseEntity getUser (Long userId) throws Exception {
        User user1;
        try {
            user1 = userRepository.findById(userId).get();

        } catch (NoSuchElementException e) {
            throw new Exception("User not found");
        } catch (CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        } catch (Exception e) {
            throw new Exception(e + "");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());
        userDTO.setVerificationType(user1.getVerificationType());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }



    public ResponseEntity updateName(Long userId, String name) throws Exception{
        User user1;
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            user1 = userOptional.get();
        }
        catch(NoSuchElementException e){
            throw new Exception("User not found");
        }

        if(!Validator.validateName(user1.getName()))
            throw new DataFormatException("Invalid name!");

        User savedUser;

        try {
            user1.setName(name);
            savedUser = userRepository.save(user1);

        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateKeyException("One of name, phone, email already exists in our Database!!");
        }
        catch(CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }

        return new ResponseEntity<>("Name updated sucessfully!",HttpStatus.OK);
    }

    //update name with email
    public ResponseEntity updateNameByEmail(String email,String name) throws DuplicateKeyException, DatabaseConnectionException {
       User user1 = new User();
        if(!Validator.validateEmail(user1.getEmail()))
            return new ResponseEntity<>("Invalid email!!!", HttpStatus.EXPECTATION_FAILED);


        try{
           Optional<User> userOptional = userRepository.findByEmail(email);
           user1 = userOptional.get();
       }
       catch(NoSuchElementException e){
           return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
       }

       try{
           user1.setName(name);
           userRepository.save(user1);
       }
      catch(DataIntegrityViolationException e){
          throw new DuplicateKeyException("One of name, phone, email already exists in our Database!!");
      }
       catch(CannotCreateTransactionException e){
           throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
       }

        return new ResponseEntity<>("Email updated successfully!",HttpStatus.OK);

    }

    public ResponseEntity getUser(String name) throws Exception {
       User user1;

        try{
            Optional<User>userOptional = userRepository.findByName(name);
            user1 = userOptional.get();
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        catch(CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }
        catch(Exception e){
            throw new Exception(e + "");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user1.getName());
        userDTO.setEmail(user1.getEmail());
        userDTO.setMobileNo(user1.getMobileNo());

        return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

    public ResponseEntity getAllUsers() throws Exception {
        User user1;
        List<User> users;
        List<UserDTO> userDTOList = new ArrayList<>();

        try {
            users = userRepository.findAll();

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("no users", HttpStatus.NOT_FOUND);
        }
        catch(CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }
        catch(Exception e){
            throw new Exception(e + "");
        }

        for(User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setMobileNo(user.getMobileNo());
            userDTO.setEmail(user.getEmail());
            userDTO.setVerificationType(user.getVerificationType());
            userDTOList.add(userDTO);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);

    }
    public ResponseEntity updateVerificationType(Long userId ,VerificationType verificationType) throws Exception {
        User user;
        try {

            Optional<User> userOptional = userRepository.findById(userId);
            user = userOptional.get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if(Validator.validateEmail(user.getEmail())) {

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

        try{
            userRepository.save(user);
        }
        catch(CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }
        catch(Exception e){
            throw new Exception(e + "");
        }
        return new ResponseEntity<>("Verification date updated " +
                                                "successfully!!!, for user with Id " + userId, HttpStatus.OK);

    }

    public ResponseEntity  addOrgsInUser(Long userId,Long orgId) throws Exception {
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

        try {
            userRepository.save(user);
        }
        catch(CannotCreateTransactionException e) {
            throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }
        catch(Exception e){
            throw new Exception(e + "");
        }
        return new ResponseEntity("Organisation added to list", HttpStatus.OK);


    }

}
