package com.AIDoc.AIDoc.controllers;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.AIDoc.AIDoc.Users.User;
import com.AIDoc.AIDoc.services.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class userController {
    private static final Logger logger = LoggerFactory.getLogger(userController.class);
    private final userService userService;
    public userController(userService userService) {
      this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
      return ResponseEntity.ok(userService.getAllUsers());
    }
 
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
      Optional<User> optionUser = userService.getUserById(id);
  
      if (optionUser.isPresent()) {
        logger.info("User found:{}", optionUser.get());
        return ResponseEntity.ok(optionUser.get());
      } else {
        logger.warn("User with ID: {} not found", id);
        return ResponseEntity.notFound().build();
      }
    }
      
    @GetMapping("/users/email/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("userEmail") String Email) {
      Optional<User> optionUser = userService.getUserByEmail(Email);
  
      if (optionUser.isPresent()) {
        logger.info("User found:{}", optionUser.get());
        return ResponseEntity.ok(optionUser.get());
      } else {
        logger.warn("User with Email: {} not found", Email);
        return ResponseEntity.notFound().build();
      }
    }
  
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
      User createdUser = userService.addUser(user);
      logger.info("User created: {}", createdUser.getUserEmail());
      return ResponseEntity.ok(createdUser);
    }
  
    @PutMapping("/users/email/{userEmail}")
    public ResponseEntity<User> updateUser(@PathVariable("userEmail") String Email, @RequestBody User updatedUser) {
      // TODO: Finish this function.
      Optional<User> optUser = userService.getUserByEmail(Email);
      if(optUser.isPresent()){
        User getUser = optUser.get();
        if(updatedUser.getUserName()!=null){
          updatedUser.setUserName(getUser.getUserName());
        }
        if(updatedUser.getUserPhone()!=null){
          updatedUser.setUserPhone(getUser.getUserPhone());
        }
        if(updatedUser.getUserEmail()!=null){
          updatedUser.setUserEmail(Email);
        }
        optUser = userService.updateUser(Email, updatedUser);
        logger.info("User updated: {}", Email);
        return ResponseEntity.ok(optUser.get());
      }
      else{
        logger.warn("User with Email: {} not found", Email);
        return ResponseEntity.notFound().build();
      }
    }
      @PutMapping("/users/{id}")
      public ResponseEntity<User> updateUserByID(@PathVariable UUID id, @RequestBody User updatedUser) {
        // TODO: Finish this function.
        Optional<User> optUser = userService.getUserById(id);
        if(optUser.isPresent()){
          User getUser = optUser.get();
          if(updatedUser.getUserName()==null){
            updatedUser.setUserName(getUser.getUserName());
          }
          if(updatedUser.getUserPhone()==null){
            updatedUser.setUserPhone(getUser.getUserPhone());
          }
          if(updatedUser.getUserEmail()==null){
            updatedUser.setUserEmail(getUser.getUserEmail());
          }
          if(updatedUser.getUserPassword()==null){
            updatedUser.setUserPassword(getUser.getUserPassword());
          }
          optUser = userService.updateUserById(id, updatedUser);
          return ResponseEntity.ok(optUser.get());
        }
      else{
        return ResponseEntity.notFound().build();
      }
    }
  
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
      userService.deleteUser(id);
      return ResponseEntity.ok().build();
    }
    @DeleteMapping("/users/email/{userEmail}")
    public ResponseEntity<Void> deleteUserbyEmail(@PathVariable("userEmail") String Email) {
      // TODO: Finish this function.
      Optional optUser = userService.getUserByEmail(Email);
      if(optUser.isPresent()){
        userService.deleteUserfromEmail(Email);
        logger.info("User with Email: {} deleted", Email);
        return ResponseEntity.ok().build();
      }
      else{
        return ResponseEntity.notFound().build();
      }
    }
}