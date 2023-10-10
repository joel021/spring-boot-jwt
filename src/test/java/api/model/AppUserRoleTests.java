package api.model;

import org.junit.jupiter.api.Test;

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
}
