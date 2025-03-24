package com.example.product_manager.service.auth;

import com.example.product_manager.common.problem.DuplicateKeyErrorProblem;
import com.example.product_manager.common.problem.InternalServerErrorProblem;
import com.example.product_manager.model.UserEntity;
import com.example.product_manager.repository.UserRepository;
import com.example.product_manager.service.auth.model.AuthResponseDto;
import com.example.product_manager.service.auth.model.NewUserDto;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.example.product_manager.service.auth.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private static final Logger log = LoggerFactory.getLogger(AuthService.class);

  private final JwtEncoder encoder;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public AuthService(
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

  public UserDto register(NewUserDto newUserDto) {
    if (userRepository.findByUsername(newUserDto.username()).isPresent()) {
      log.error("Username is already taken, username={}", newUserDto.username());
      throw new DuplicateKeyErrorProblem();
    }

    UserEntity entity =
        new UserEntity(
            null, newUserDto.username(), passwordEncoder.encode(newUserDto.password()), "USER");

    try {
      entity = userRepository.save(entity);
      return UserDtoMapper.toUserDto(entity);
    } catch (Exception e) {
      log.error(
          "Unable to save new user due to an unexpected error, message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }
}
