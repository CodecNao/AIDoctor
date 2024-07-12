package com.AIDoc.AIDoc;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
  
    public User addUser(User user) {
      userMapper.insert(user);;
      return user;
    }
  
    public Optional<User> updateUser(UUID id, User updatedUser) {
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
      System.out.println("User:"+id+" is deleted correctly");
    }
}
