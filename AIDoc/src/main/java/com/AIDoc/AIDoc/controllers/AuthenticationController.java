package com.AIDoc.AIDoc.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AIDoc.AIDoc.Users.RegisterOfUser;
import com.AIDoc.AIDoc.Users.User;
import com.AIDoc.AIDoc.services.AuthenticationService;
import com.AIDoc.AIDoc.Users.LoginResponse;
import com.AIDoc.AIDoc.Users.LoginUser;
import com.AIDoc.AIDoc.services.JWTService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  private final AuthenticationService authenticationService;
  private final JWTService jwtService;
  public AuthenticationController(AuthenticationService authenticationService,JWTService jwtService) {
    this.authenticationService = authenticationService;
    this.jwtService=jwtService;
  }

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterOfUser registerUserDto) {
    logger.info("Signup request received with the following data: {}", registerUserDto);
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUser loginUser) {
    logger.info("Login request received with the following data: {}", loginUser);
    User authenticatedUser = authenticationService.authenticate(loginUser);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }
}
