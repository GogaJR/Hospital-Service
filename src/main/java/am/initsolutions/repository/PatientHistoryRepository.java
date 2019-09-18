package am.initsolutions.repository;

import am.initsolutions.models.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {
    List<PatientHistory> findAllByPatientId(Long patientId);

}
