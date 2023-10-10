package api.model;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
  ROLE_USER, ROLE_MANAGER, ROLE_ADMIN;

  public String getAuthority() {
    return name();
  }

}
