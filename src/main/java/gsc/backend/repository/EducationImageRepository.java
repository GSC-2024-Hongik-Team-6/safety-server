package gsc.backend.repository;

import gsc.backend.domain.EducationImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationImageRepository extends JpaRepository<EducationImage, Long> {

    List<EducationImage> findAllByEducation_Id(Long educationId);
}
