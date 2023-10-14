package api.controller;

import api.exception.ResourceAlreadyExists;
import api.model.AppUser;
import api.model.AppUserRole;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ControllerTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;

    String adminToken;
    String userToken;
    AppUser adminUser;
    AppUser userUser;

    public void setup() throws ResourceAlreadyExists {

        adminUser = new AppUser("adminUserName","Professor 007", "Admin@mail.com", "password",
                true, AppUserRole.ROLE_ADMIN);
        userUser = new AppUser("userUserName", "Mr. Bean", "userUser@mail.com",
                "password", true, AppUserRole.ROLE_USER);

        adminToken = userService.signup(adminUser);
        userToken = userService.signup(userUser);

    }
}
