package api.controller;

import api.model.AppUser;
import api.model.AppUserRole;
import api.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    private AppUser adminUser;
    private AppUser userUser;
    private String adminToken;
    private String userToken;


    @BeforeEach
    public void setup() {

        adminUser = new AppUser("adminUserName", "Admin@mail.com", "password", true, AppUserRole.ROLE_ADMIN);
        adminToken = userService.signup(adminUser);

        userUser = new AppUser("userUserName", "userUser@mail.com", "password", true, AppUserRole.ROLE_USER);
        userToken = userService.signup(userUser);
    }

    @AfterEach
    public void afterEach() {
        userService.delete(adminUser.getUsername());
        userService.delete(userUser.getUsername());
    }

    @Test
    public void findStudentByRegisterNotExistsTest() throws Exception {

        mockMvc.perform(
                        get("/student/000293")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + adminToken))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findStudentByRegisterNotExistsAndNotAuthorizedTest() throws Exception {

        mockMvc.perform(
                        get("/student/000293")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

}