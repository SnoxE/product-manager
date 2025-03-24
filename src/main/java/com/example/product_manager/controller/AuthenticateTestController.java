package com.example.product_manager.controller;

import com.example.product_manager.AuthRequestDto;
import com.example.product_manager.AuthResponseDto;
import com.example.product_manager.AuthenticationService;
import com.example.product_manager.NewUserDto;
import com.example.product_manager.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateTestController {
  private static final Logger log = LoggerFactory.getLogger(AuthenticateTestController.class);

  private final AuthenticationService authenticationService;
  private final AuthenticationManager authenticationManager;

  public AuthenticateTestController(
      AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
    this.authenticationService = authenticationService;
    this.authenticationManager = authenticationManager;
  }

  @GetMapping("/api/open")
  public String open() {
    return "This endpoint is open";
  }

  @PostMapping("/api/login")
  public AuthResponseDto login(@RequestBody AuthRequestDto request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    return authenticationService.generateToken(authentication);
  }

  @PostMapping("/api/register")
  public UserEntity register(@RequestBody NewUserDto newUser) {
    return authenticationService.register(newUser);
  }

  @GetMapping("/api/authenticated")
  public String authenticated() {
    return "This endpoint required authentication";
  }

  @GetMapping("/api/authenticated/admin")
  public String authenticatedAdmin() {
    return "This endpoint required authentication and is only visible to users with admin role";
  }
}
