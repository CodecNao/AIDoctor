package com.AIDoc.AIDoc.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.AIDoc.AIDoc.Users.RegisterOfUser;
import com.AIDoc.AIDoc.Users.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.AIDoc.AIDoc.Users.LoginUser;
@Service
public class AuthenticationService {

  private final userService userService;

  private final PasswordEncoder passwordEncoder;
  
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(userService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public User signup(RegisterOfUser input) {
    User user = new User();
    user.setUserEmail(input.getEmail());
    System.out.println("input.getPassword()="+input.getPassword());
    user.setUserPassword(passwordEncoder.encode(input.getPassword()));
    System.out.println("encoder done!");
    user.setUserName(input.getName());
    user.setUserPhone(input.getPhone());
    return userService.addUser(user);
  }
  public User authenticate(LoginUser input) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword());
    authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    return userService.getUserByEmail(input.getEmail()).orElseThrow();
  }
}
