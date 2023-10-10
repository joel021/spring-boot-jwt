package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, String> {


}
