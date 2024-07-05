package com.AIDoc.AIDoc;
import java.util.Optional;

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
    private userService UserService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
      return ResponseEntity.ok(UserService.getAllUsers());
    }
  
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
      Optional<User> optionUser = UserService.getUserById(id);
  
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
  
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
      // TODO: Finish this function.
      Optional optUser = UserService.getUserById(id);
      if(optUser.isPresent()){
        optUser = UserService.updateUser(id, updatedUser);
        return ResponseEntity.ok(optUser.get());
      }
      else{
        return ResponseEntity.notFound().build();
      }
    }
  
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
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
}
