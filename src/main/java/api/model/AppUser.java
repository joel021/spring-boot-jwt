package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {

  @Id
  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @NotBlank
  private String name;

  @Column(unique = true, nullable = false)
  @Email
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  private boolean enabled;
  private AppUserRole appUserRole;

  @Override
  public List<AppUserRole> getAuthorities() {

    if (appUserRole == null) {
      return Collections.singletonList(AppUserRole.ROLE_USER);
    }

    List<AppUserRole> appUserRoles = new ArrayList<>();

    for(AppUserRole ordinalRole: AppUserRole.values()) {
      if (appUserRole.ordinal() >= ordinalRole.ordinal()){
        appUserRoles.add(ordinalRole);
      }
    }
    return appUserRoles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  public static AppUser instanceFrom(AppUser user) {

    AppUser userDatails = new AppUser();
    userDatails.setUsername(user.getUsername());
    userDatails.setAppUserRole(user.getAppUserRole());
    userDatails.setEnabled(user.isEnabled());
    userDatails.setEmail(user.getEmail());
    userDatails.setName(user.getName());
    userDatails.setPassword(user.getPassword());

    return userDatails;
  }
}
