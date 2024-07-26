package com.example.DocFlow.Controller;

import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Enums.VerificationType;
import com.example.DocFlow.Exceptions.DatabaseConnectionException;
import com.example.DocFlow.Exceptions.DuplicateKeyException;
import com.example.DocFlow.Repository.UserRepository;
import com.example.DocFlow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity addUser (@RequestBody User user) {
        try {
            return userService.addUser(user);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity getUser (@RequestParam("userId") Long userId) throws Exception {
        try {
            return userService.getUser(userId);
        } catch (Exception e) {
          return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateNameByUserId")
    public ResponseEntity updateUser (@RequestParam("userId") Long userId, @RequestParam("name") String name) {
       try {
           return userService.updateName(userId, name);
       }
       catch(Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/updateNameByEmail")
    public ResponseEntity updateUserName (@RequestParam("email") String email, @RequestParam("name") String name) throws DatabaseConnectionException, DuplicateKeyException {
        try {
            return userService.updateNameByEmail(email, name);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getName")
    public ResponseEntity getUserByName (@RequestParam("name") String name) throws Exception {

        try {
            return userService.getUser(name);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() throws Exception {

        try {
            return userService.getAllUsers();

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateVerificationType")
    public ResponseEntity updateVerificationType(@RequestParam("userId")Long userId,
                                                 @RequestParam("VerificationType") VerificationType verificationType) throws Exception {
        try {
            return userService.updateVerificationType(userId, verificationType);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addOrgInUsers")
    public ResponseEntity addOrgInUsers(@RequestParam("userId")Long userId,@RequestParam("orgId")Long orgId) throws Exception {
      try{
          return userService.addOrgsInUser(userId,orgId);
      }
      catch(Exception e){
          return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }
}