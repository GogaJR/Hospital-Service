package am.initsolutions.repository;

import am.initsolutions.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    //List<Doctor> findAllByDepartmentId(Long departmentId);
    Page<Doctor> findAllByDepartmentId(Pageable pageable,Long departmentId);
    Doctor findByUserId(long id);
    void deleteByHospitalId(Long hospitalId);

}
