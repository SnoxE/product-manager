package com.example.product_manager;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final JwtEncoder encoder;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public AuthenticationService(
      JwtEncoder encoder, PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.encoder = encoder;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public AuthResponseDto generateToken(Authentication authentication) {
    Instant now = Instant.now();

    List<String> roles =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    JwtClaimsSet claimsSet =
        JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("roles", roles)
            .build();

    return new AuthResponseDto(
        this.encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue(), roles);
  }

  public UserEntity register(NewUserDto newUserDto) {
    if (userRepository.findByUsername(newUserDto.username()).isPresent()) {
      //      TODO
      throw new RuntimeException("Username already taken");
    }

    UserEntity entity =
        new UserEntity(
            null, newUserDto.username(), passwordEncoder.encode(newUserDto.password()), "USER");

    try {
      return userRepository.save(entity);
    } catch (Exception e) {
      //      TODO
      // Log the error (you can use a logger instead)
      //      System.err.println("Failed to save user: " + e.getMessage());
      //      UsernameAlreadyExistsException, RegistrationException
      throw new RuntimeException("Registration failed. Please try again later.");
    }
  }
}
