package school.springboot.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.springboot.auth.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, String> {


}
