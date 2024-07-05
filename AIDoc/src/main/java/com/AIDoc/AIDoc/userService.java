package com.AIDoc.AIDoc;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class userService {
    private final MockDatabase mockDatabase;

    public userService(@Autowired MockDatabase mockDatabase) {
      this.mockDatabase = mockDatabase;
    }
  
    public List<User> getAllUsers() {
      return mockDatabase.getUsers();
    }
  
    public Optional<User> getUserById(Long id) {
      return mockDatabase.getUserById(id);
    }
  
    public User addUser(User user) {
      mockDatabase.addUser(user);
      return user;
    }
  
    public Optional<User> updateUser(Long id, User updatedUser) {
      // TODO: Finish this function.
      Optional<User> optUser = mockDatabase.getUserById(id);
      if(optUser.isPresent()){
        return mockDatabase.updateUser(id, updatedUser);
      }
      throw new UnsupportedOperationException();
    }
  
    public void deleteUser(Long id) {
      // TODO: Finish this function.
      if(mockDatabase.getUserById(id).isPresent()){
        mockDatabase.deleteUser(id);
      }else{
        throw new UnsupportedOperationException();
      }
    }
}
