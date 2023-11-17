package school.springboot.auth.service;

import school.springboot.exception.CustomException;
import lombok.RequiredArgsConstructor;
import school.springboot.auth.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import school.springboot.auth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public AppUser findById(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findById(username);
        if (!appUser.isPresent()) {
          throw new CustomException("Este usuário não foi encontrado.", HttpStatus.NOT_FOUND);
        }
        return appUser.get();
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return AppUser.instanceFrom(findById(username));
    }

}
