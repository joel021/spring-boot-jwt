package api.service;

import javax.servlet.http.HttpServletRequest;

import api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.exception.CustomException;
import api.model.AppUser;
import api.repository.UserRepository;
import api.security.JwtTokenProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;


  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findById(username).get().getAuthorities());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser appUser) {
    return jwtTokenProvider.createToken(create(appUser));
  }

  public AppUser create(AppUser appUser) {
    if (!userRepository.findById(appUser.getUsername()).isPresent()) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      return userRepository.save(appUser);
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteById(username);
  }

  public AppUser findById(String username) throws ResourceNotFoundException {
    Optional<AppUser> optionalAppUser = userRepository.findById(username);
    if (optionalAppUser.isPresent()) {

    }
    throw new ResourceNotFoundException("The user doesn't exist");
  }

  public AppUser whoami(HttpServletRequest req) {
    return userRepository.findById(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).get();
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findById(username).get().getAuthorities());
  }

}
