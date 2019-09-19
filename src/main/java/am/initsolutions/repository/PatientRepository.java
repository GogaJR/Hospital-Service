package am.initsolutions.repository;

import am.initsolutions.models.Patient;
import am.initsolutions.models.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserId(Long userId);

    @Modifying
    @Query(value="SELECT * FROM patient INNER JOIN patient_doctor ON patient.id=patient_doctor.patient_id where patient_doctor.doctor_id=:doctorId",
            nativeQuery = true)
    List<Patient> patientListByDoctorId(@Param("doctorId") long doctorId);

    @Query(nativeQuery = true, value = "SELECT doctor_id FROM patient_doctor WHERE patient_id = :id")
    List<BigInteger> getDoctors(@Param("id") Long id);


}
