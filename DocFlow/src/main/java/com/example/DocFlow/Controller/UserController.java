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

    @PostMapping("/add")
    public ResponseEntity addUser (@RequestBody User user) {
        return userService.addUser(user);

    }

    @GetMapping("/get")
    public ResponseEntity getUser (@RequestParam("userId") Long userId) {
        return userService.getUser(userId);

    }

//    @GetMapping("/getUsers")
//    public ResponseEntity<List<User>> getUsers(){
//      List<User> list = userService.getAllUsers();
//      if(list.size() <= 0){
//          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//      }
//      return ResponseEntity.of(Optional.of(list));
//    }


    @PutMapping("/update")
    public ResponseEntity updateUser (@RequestParam("userId") Long userId, @RequestParam("name") String name) {
        return userService.updateName(userId, name);

    }

    @PutMapping("/updateName")
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