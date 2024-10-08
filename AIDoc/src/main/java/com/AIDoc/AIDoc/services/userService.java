package com.AIDoc.AIDoc.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AIDoc.AIDoc.mappers.*;
import com.AIDoc.AIDoc.Users.*;
@Service
public class userService {
    private final UserMapper userMapper;

    public userService(@Autowired UserMapper userMapper) {
      this.userMapper = userMapper;
    }
  
    public List<User> getAllUsers() {
      return userMapper.findAll();
    }
 
    public Optional<User> getUserById(UUID id) {
      return Optional.ofNullable(userMapper.findById(id));
    }
    public Optional<User> getUserByEmail(String Email) {
      return Optional.ofNullable(userMapper.findByEmail(Email));
    }
  
    public User addUser(User user) {
      userMapper.insert(user);
      return user;
    }
  
    public Optional<User> updateUser(String Email, User updatedUser) {
      // TODO: Finish this function.
      Optional<User> optUser = Optional.ofNullable(userMapper.findByEmail(Email));
      if(optUser.isPresent()){
        userMapper.update(updatedUser);
        return Optional.of(updatedUser);
      }
      throw new UnsupportedOperationException();
    }
    public Optional<User> updateUserById(UUID id, User updatedUser) {
      // TODO: Finish this function.
      Optional<User> optUser = Optional.ofNullable(userMapper.findById(id));
      if(optUser.isPresent()){
        userMapper.update(updatedUser);
        return Optional.of(updatedUser);
      }
      throw new UnsupportedOperationException();
    }
    public void deleteUser(UUID id) {
      // TODO: Finish this function.

      userMapper.delete(id);
      System.out.println("User:"+id.toString()+" is deleted correctly");
    }
    public void deleteUserfromEmail(String Email) {
      // TODO: Finish this function.

      userMapper.deletefromEmail(Email);
      System.out.println("User:"+Email+" is deleted correctly");
    }
}
