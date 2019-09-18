package am.initsolutions.repository;

import am.initsolutions.models.Department;
import am.initsolutions.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByDepartmentId(Long departmentId);
    Doctor findByUserId(long id);

//    @Query(value="SELECT patient_id FROM patient_doctor WHERE doctor_id=:doctorId",
//            nativeQuery = true)
//    List<Long> patientListByDoctorId(@Param("doctorId") long doctorId);
}
