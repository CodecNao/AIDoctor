package com.AIDoc.AIDoc.services;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.AIDoc.AIDoc.Users.User;
@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final userService userService;
  public CustomUserDetailsService(userService userService) {
    this.userService = userService;
  }
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userService.getUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
        new ArrayList<>());
  }
}