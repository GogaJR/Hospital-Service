package am.initsolutions.services;

import am.initsolutions.forms.DepartmentForm;
import am.initsolutions.models.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    void deleteDepartment(Long id);
    void update(Department department);
    Department get(Long id);
    Department add(DepartmentForm departmentForm);
    List<Department> getAll();
    Page<Department> getAll(Pageable pageable);
}
