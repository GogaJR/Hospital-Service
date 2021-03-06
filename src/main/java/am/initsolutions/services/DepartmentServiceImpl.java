package am.initsolutions.services;

import am.initsolutions.forms.DepartmentForm;
import am.initsolutions.models.Department;
import am.initsolutions.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void deleteDepartment(Long id) {
       departmentRepository.delete(id);

    }

    @Override
    public void update(Department department) {
        Department dep = departmentRepository.findOne(department.getId());
        dep.setName(department.getName());
        departmentRepository.save(dep);
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findOne(id);
    }

    //add hospital
    @Override
    public Department add(DepartmentForm departmentForm) {
        Department dep = Department.builder()
                .name(departmentForm.getName())
                .build();
             return departmentRepository.save(dep);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Page<Department> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }
}
