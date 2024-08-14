package com.AIDoc.AIDoc;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class userController {
    @Autowired
    private userService UserService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
      return ResponseEntity.ok(UserService.getAllUsers());
    }
 
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
      Optional<User> optionUser = UserService.getUserById(id);
  
      if (optionUser.isPresent()) {
        return ResponseEntity.ok(optionUser.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    }
      
    @GetMapping("/users/email/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("userEmail") String Email) {
      Optional<User> optionUser = UserService.getUserByEmail(Email);
  
      if (optionUser.isPresent()) {
        return ResponseEntity.ok(optionUser.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    }
  
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
      User createdUser = UserService.addUser(user);
      return ResponseEntity.ok(createdUser);
    }
  
    @PutMapping("/users/email/{userEmail}")
    public ResponseEntity<?> updateUser(@PathVariable("userEmail") String Email, @RequestBody User updatedUser) {
      // TODO: Finish this function.
      Optional<User> optUser = UserService.getUserByEmail(Email);
      if(optUser.isPresent()){
        User getUser = optUser.get();
        if(updatedUser.getUserName()==null){
          updatedUser.setUserName(getUser.getUserName());
        }
        if(updatedUser.getUserPhone()==null){
          updatedUser.setUserPhone(getUser.getUserPhone());
        }
        if(updatedUser.getUserEmail()==null){
          updatedUser.setUserEmail(Email);
        }
        optUser = UserService.updateUser(Email, updatedUser);
        return ResponseEntity.ok(optUser.get());
      }
      else{
        return ResponseEntity.notFound().build();
      }
    }
      @PutMapping("/users/{id}")
      public ResponseEntity<?> updateUserByID(@PathVariable UUID id, @RequestBody User updatedUser) {
        // TODO: Finish this function.
        Optional<User> optUser = UserService.getUserById(id);
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
          optUser = UserService.updateUserById(id, updatedUser);
          return ResponseEntity.ok(optUser.get());
        }
      else{
        return ResponseEntity.notFound().build();
      }
    }
  
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
      // TODO: Finish this function.
      Optional optUser = UserService.getUserById(id);
      if(optUser.isPresent()){
        UserService.deleteUser(id);
        return ResponseEntity.ok(optUser.get());
      }
      else{
        return ResponseEntity.notFound().build();
      }
    }
    @DeleteMapping("/users/email/{userEmail}")
    public ResponseEntity<?> deleteUserbyEmail(@PathVariable("userEmail") String Email) {
      // TODO: Finish this function.
      Optional optUser = UserService.getUserByEmail(Email);
      if(optUser.isPresent()){
        UserService.deleteUserfromEmail(Email);
        return ResponseEntity.ok(optUser.get());
      }
      else{
        return ResponseEntity.notFound().build();
      }
    }
}