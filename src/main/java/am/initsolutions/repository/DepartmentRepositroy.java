package am.initsolutions.repository;

import am.initsolutions.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepositroy extends JpaRepository<Department,Integer> {

}
