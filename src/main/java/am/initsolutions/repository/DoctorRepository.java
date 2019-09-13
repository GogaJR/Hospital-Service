package am.initsolutions.repository;

import am.initsolutions.models.Department;
import am.initsolutions.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByDepartmentId(Long departmentId);
}
