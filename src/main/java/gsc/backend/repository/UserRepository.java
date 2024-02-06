package gsc.backend.repository;

import gsc.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUuid(String UserUuid);
}