package api.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class AppUserTests {

    @Test
    public void getAuthoritiesUserTest() {

        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "email@email.com", "password", true, AppUserRole.ROLE_USER);
        assertTrue(appUser.getAuthorities().contains(AppUserRole.ROLE_USER),"Assert user have the assigned role.");
    }

    @Test
    public void getAuthoritiesAdminContainsUserTest() {

        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "email@email.com", "password", true, AppUserRole.ROLE_ADMIN);
        assertTrue(appUser.getAuthorities().contains(AppUserRole.ROLE_USER),"Assert superior role have inferior.");
    }

    @Test
    public void getAuthoritiesUserDoesNotContainsAdminTest() {

        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "email@email.com", "password", true, AppUserRole.ROLE_USER);
        assertFalse(appUser.getAuthorities().contains(AppUserRole.ROLE_ADMIN), "Assert inferior role does not have superior.");
    }

    @Test
    public void instanceFromAssertCopy() {

        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "email@email.com", "password", true, AppUserRole.ROLE_USER);
        AppUser appUserCopy = AppUser.instanceFrom(appUser);
        assertEquals(appUserCopy.getUsername(), appUser.getUsername(), "Assert it returns the copy of the object");
    }

    @Test
    public void instanceFromAssertDifferentMemoryReference() {

        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "email@email.com", "password", true, AppUserRole.ROLE_USER);
        AppUser appUserCopy = AppUser.instanceFrom(appUser);
        assertNotEquals(appUserCopy, appUser, "Assert the instances are in different memory position.");
    }
}