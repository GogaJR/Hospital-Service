package am.initsolutions.repository;

import am.initsolutions.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
