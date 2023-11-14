package auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, String> {


}
