package com.example.DocFlow.Controller;

import com.example.DocFlow.ENums.VerificationType;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Repository.UserRepository;

import com.example.DocFlow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userService.addUser(user);

    }

    @GetMapping("/getUser")
    public ResponseEntity getUser (@RequestParam("userId") Long userId) {
        return userService.getUser(userId);

    }

    @PutMapping("/updateNameByUserId")
    public ResponseEntity updateUser (@RequestParam("userId") Long userId, @RequestParam("name") String name) {
        return userService.updateName(userId, name);

    }

    @PutMapping("/updateNameByEmail")
    public ResponseEntity updateUserName (@RequestParam("email") String email, @RequestParam("name") String name) {
        return userService.updateNameByEmail(email, name);
    }

    @GetMapping("/getName")
    public ResponseEntity getUserByName (@RequestParam("name") String name) {

        return userService.getUser(name);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers(){
        return userService.getAllUsers();

    }
    @PutMapping("/updateVerificationType")
    public ResponseEntity updateVerificationType(@RequestParam("userId")Long userId,
                                                 @RequestParam("VerificationType")VerificationType verificationType){
      return userService.updateVerificationType(userId, verificationType);
    }
}