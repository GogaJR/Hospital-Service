package am.initsolutions.repository;

import am.initsolutions.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT doctor_id FROM patient_doctor WHERE patient_id = :id")
    List<Long> getDoctors(@Param("id") Long id);
}
