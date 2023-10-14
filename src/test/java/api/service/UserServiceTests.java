package api.service;

import api.exception.ResourceAlreadyExists;
import api.exception.ResourceNotFoundException;
import api.exception.UnauthorizedException;
import api.model.AppUser;
import api.model.AppUserRole;
import api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private AppUser user;

    private AppUser userTobeCreated;


    @BeforeEach
    public void setup() {

        user = new AppUser("username_to_test", "name", "email", "password", true,
                AppUserRole.ROLE_ADMIN);
        userTobeCreated = new AppUser("username_to_be_created", "name", "email", "password", true,
                AppUserRole.ROLE_ADMIN);

        AppUser userSaved = AppUser.instanceFrom(user);
        userSaved.setPassword(new BCryptPasswordEncoder().encode(userSaved.getPassword()));

        AppUser userToBeCreatedSaved = AppUser.instanceFrom(userTobeCreated);
        userToBeCreatedSaved.setPassword(new BCryptPasswordEncoder().encode(userToBeCreatedSaved.getPassword()));

        when(userRepository.save(user)).thenReturn(userSaved);
        when(userRepository.save(userTobeCreated)).thenReturn(userToBeCreatedSaved);
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(userSaved));
    }

    @Test
    public void signinTest() throws UnauthorizedException {

        String token = userService.signin(user.getUsername(), user.getPassword());
        assertNotNull(token, "Create token when credentials are valid.");
    }

    @Test
    public void signinWrongPassTest() {

        Exception exception = assertThrows(UnauthorizedException.class, () -> {
            userService.signin(user.getUsername(), "wrong_pass");
        });
        assertNotNull(exception.getMessage(), "Assert there is a message of the error.");
    }

    @Test
    public void signinWithoutUsernameTest() {

        Exception exception = assertThrows(UnauthorizedException.class, () -> {
            userService.signin("user_does_not_exists", user.getPassword());
        });
        assertNotNull(exception.getMessage(), "Assert return error specifying that the user does not exists.");
    }

    @Test
    public void createTest() throws ResourceAlreadyExists {

        AppUser actualUser = userService.create(userTobeCreated);
        assertSame(userTobeCreated.getUsername(), actualUser.getUsername(), "Assert the user is created.");
    }

    @Test
    public void createWhenAlreadyExistsTest() {

        Exception exception = assertThrows(ResourceAlreadyExists.class, () -> {
            userService.create(user);
        });

        assertNotNull(exception.getMessage(), "Assert throws an error when the user already exists.");
    }

    @Test
    public void findByIdTest() throws ResourceNotFoundException {

        AppUser actualUser = userService.findById(user.getUsername());
        assertEquals(actualUser.getUsername(), user.getUsername(), "Assert the user is found.");
    }

    @Test
    public void findByIdWhenDoesNotExists() {

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.findById("this_user_does_not_exists");
        });
        assertNotNull(exception.getMessage(), "Raise an exception informing the user does not exists.");
    }

    @Test
    public void deleteTest() {

        userService.delete(user.getUsername());
    }

}
