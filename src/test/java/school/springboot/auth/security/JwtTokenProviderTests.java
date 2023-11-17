package school.springboot.auth.security;

import school.springboot.auth.security.JwtTokenProvider;
import school.springboot.exception.CustomException;
import school.springboot.auth.model.AppUser;
import school.springboot.auth.model.AppUserRole;
import school.springboot.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JwtTokenProviderTests {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String token;

    private AppUser appUser;

    @BeforeEach
    public void setup() {

        appUser = new AppUser("username", "name", "email@email.com", "password",
                true, AppUserRole.ROLE_ADMIN);

        token = Jwts.builder()
                .setClaims(Jwts.claims().setSubject(appUser.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 60*60*1000))
                .signWith(SignatureAlgorithm.HS256, jwtTokenProvider.getSecretKey())
                .compact();

        AppUser user = AppUser.instanceFrom(appUser);
        when(userRepository.findById(appUser.getUsername())).thenReturn(Optional.of(user));
    }

    @Test
    public void resolveTokenTest() {

        String actualToken = jwtTokenProvider.resolveToken("Bearer "+token);
        assertEquals(token, actualToken, "Test whether the token is extracted correctly.");
    }

    @Test
    public void resolveTokenInvalidTest() {

        String actualToken = jwtTokenProvider.resolveToken("Bearer ");
        assertEquals("", actualToken, "Test whether the method return empty string.");
    }

    @Test
    public void resolveTokenNullTest() {

        String actualToken = jwtTokenProvider.resolveToken(null);
        assertNull(actualToken, "Test whether the method does not raise exception.");
    }

    @Test
    public void createTokenTest() {

        String actualToken = jwtTokenProvider.createToken(appUser);
        assertNotNull(actualToken, "Assert the token is created.");
    }

    @Test
    public void createTokenByNullUserTest() {

        String actualToken = jwtTokenProvider.createToken(null);
        assertNull(actualToken, "Assert the token is null.");
    }

    @Test
    public void createTokenByNullRulesTest() {

        String actualToken = jwtTokenProvider.createToken(null, new ArrayList<>());
        assertNull(actualToken, "Assert the token is null.");
    }

    @Test
    public void validTokenTest() {

        boolean actualToken = jwtTokenProvider.validateToken(token);
        assertTrue(actualToken, "Assert the valid token is accepted.");
    }

    @Test
    public void validTokenInvalidTest() {

        Exception exception = assertThrows(CustomException.class, () -> {
            jwtTokenProvider.validateToken("3gt63"+token.substring(5, token.length()-2)+"k");
        });
        assertFalse(exception.getMessage().isEmpty(), "Assert the invalid token is not accepted.");
    }

    @Test
    public void validTokenNullTest() {

        Exception exception = assertThrows(CustomException.class, () -> {
            jwtTokenProvider.validateToken(null);
        });
        assertFalse(exception.getMessage().isEmpty(), "Assert the null valid token is not accepted.");
    }

    @Test
    public void getAuthenticationTest() {

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        assertTrue(authentication.isAuthenticated(), "Assert the user is authenticated.");
    }

    @Test
    public void getUsernameTest() {

        String actualUsername = jwtTokenProvider.getUsername(token);
        assertEquals(appUser.getUsername(), actualUsername, "Assert the method can retrieve the username stored in token.");
    }

    @Test
    public void createValidateTest() {

        boolean actualValidate = jwtTokenProvider.validateToken(jwtTokenProvider.createToken(appUser));
        assertTrue(actualValidate, "");
    }

    @Test
    public void createAndAuthenticateTest() {

        Authentication authenticated = jwtTokenProvider.getAuthentication(jwtTokenProvider.resolveToken("Bearer "
                + jwtTokenProvider.createToken(appUser)));

        assertTrue(authenticated.isAuthenticated(), "Assert the valid token is accepted.");
    }


}
