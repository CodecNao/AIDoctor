package com.AIDoc.AIDoc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class MockDatabase {
  private final List<User> users = new ArrayList<>();
  private final AtomicLong counter = new AtomicLong();

  public List<User> getUsers() {
    return users;
  }

  public User addUser(User user) {
    user.setUserID(counter.incrementAndGet());
    users.add(user);
    return user;
  }


public Optional<User> getUserById(Long id) {
    for(User user:users){
        if(user.getUserID()==id){
            return Optional.ofNullable(user);
        }
    }
    return Optional.ofNullable(null);
  }

  public Optional<User> updateUser(Long id, User updatedUser) {
    return getUserById(id).map(user -> {
      user.setUserName(updatedUser.getUserName());
      user.setUserEmail(updatedUser.getUserEmail());
      user.setUserPhone(updatedUser.getUserPhone());
      return user;
    });
  }

  public boolean deleteUser(Long id) {
    for(User user:users){
        if(user.getUserID()==id){
            users.remove(user);
            return true;
        }
    }
    return false;
  }
}