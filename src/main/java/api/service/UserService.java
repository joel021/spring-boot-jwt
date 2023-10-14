package api.service;

import api.exception.ResourceAlreadyExists;
import api.exception.ResourceNotFoundException;
import api.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


  public String signin(String username, String password) throws UnauthorizedException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, findById(username).getAuthorities());
    } catch (ResourceNotFoundException | AuthenticationException e) {
      throw new UnauthorizedException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser appUser) throws ResourceAlreadyExists {
    return jwtTokenProvider.createToken(create(appUser));
  }

  public AppUser create(AppUser appUser) throws ResourceAlreadyExists {
    if (!userRepository.findById(appUser.getUsername()).isPresent()) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      return userRepository.save(appUser);
    } else {
      throw new ResourceAlreadyExists("Username is already in use");
    }
  }

  public void delete(String username) {
    userRepository.deleteById(username);
  }

  public AppUser findById(String username) throws ResourceNotFoundException {
    Optional<AppUser> optionalAppUser = userRepository.findById(username);
    if (optionalAppUser.isPresent()) {
      return optionalAppUser.get();
    }
    throw new ResourceNotFoundException("The user doesn't exist");
  }

}
