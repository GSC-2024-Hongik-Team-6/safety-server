package gsc.backend.repository;

import gsc.backend.domain.Education;
import gsc.backend.domain.User;
import gsc.backend.domain.mapping.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEducationRepository extends JpaRepository<UserEducation, Long> {

    UserEducation findByUserAndEducation(User user, Education education);
}
