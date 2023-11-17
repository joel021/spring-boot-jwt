package school.springboot.api.controller;

import school.springboot.exception.ResourceAlreadyExists;
import school.springboot.auth.model.AppUser;
import school.springboot.auth.model.AppUserRole;
import school.springboot.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ControllerTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;

    String adminToken;
    String professorToken;
    String userToken;
    AppUser adminUser;
    AppUser professorUser;
    AppUser userUser;

    public void setup() throws ResourceAlreadyExists {

        adminUser = new AppUser("adminUsername","Professor 007", "Admin@mail.com", "password",
                true, AppUserRole.ROLE_ADMIN);
        userUser = new AppUser("userUsername", "Mr. Bean", "userUser@mail.com",
                "password", true, AppUserRole.ROLE_USER);
        professorUser = new AppUser("professorUsername", "Mr. Bean", "professor@mail.com",
                "password", true, AppUserRole.ROLE_PROFESSOR);
        adminToken = userService.signinOrsignup(adminUser);
        userToken = userService.signinOrsignup(userUser);
        professorToken = userService.signinOrsignup(professorUser);
    }
}
