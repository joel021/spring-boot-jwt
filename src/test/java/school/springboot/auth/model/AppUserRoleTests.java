package school.springboot.auth.model;

import org.junit.jupiter.api.Test;
import school.springboot.auth.model.AppUserRole;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppUserRoleTests {

    @Test
    public void hasUserTest() {

        List<AppUserRole> values = Arrays.asList(AppUserRole.values());
        assertTrue(values.contains(AppUserRole.ROLE_USER), "Assert the roles set contains USER");
    }

    @Test
    public void hasManagerTest() {

        List<AppUserRole> values = Arrays.asList(AppUserRole.values());
        assertTrue(values.contains(AppUserRole.ROLE_MANAGER), "Assert the roles set contains MANAGER");
    }

    @Test
    public void hasAdminTest() {

        List<AppUserRole> values = Arrays.asList(AppUserRole.values());
        assertTrue(values.contains(AppUserRole.ROLE_ADMIN), "Assert the roles set contains ADMIN");
    }

    @Test
    public void hasProfessorTest() {

        List<AppUserRole> values = Arrays.asList(AppUserRole.values());
        assertTrue(values.contains(AppUserRole.ROLE_PROFESSOR), "Assert the roles set contains ROLE_PROFESSOR");
    }
}
