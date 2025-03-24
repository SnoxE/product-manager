package com.example.product_manager.controller;

import com.example.product_manager.service.auth.AuthService;
import com.example.product_manager.service.auth.model.AuthRequestDto;
import com.example.product_manager.service.auth.model.AuthResponseDto;
import com.example.product_manager.service.auth.model.NewUserDto;
import com.example.product_manager.service.auth.model.UserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;

  public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping(
      value = "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AuthResponseDto login(@RequestBody @Valid AuthRequestDto request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    return authService.generateToken(authentication);
  }

  @PostMapping(
      value = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.CREATED)
  public UserDto register(@RequestBody @Valid NewUserDto newUser) {
    return authService.register(newUser);
  }
}
