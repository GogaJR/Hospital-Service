package am.initsolutions;

import am.initsolutions.models.Department;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.services.DepartmentService;
import am.initsolutions.services.DepartmentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentTest {

private DepartmentServiceImpl departmentService;

private DepartmentRepository departmentRepository;

@Before
public void setUp() {
    departmentRepository = mock(DepartmentRepository.class);
    departmentService = new DepartmentServiceImpl(departmentRepository);
}
    @Test
    public void getDepartmentSize() {
        //System.out.println(departmentService.getAll().size());
        when(departmentRepository.findAll()).thenReturn(Stream.of(new Department((long) 1,"Test"),new Department((long) 2,"test2")).collect(Collectors.toList()));
        Assert.assertEquals(2,departmentService.getAll().size());

    }



    @Test
    public void saveDep(){
    Department department=new Department((long) 5,"Testul");
    when(departmentRepository.save(department)).thenReturn(department);
    Assert.assertEquals(department,departmentService.add(department));

    }

    @Test
    public void deleteDep(){
        Department department=new Department((long) 5,"Testul");
        departmentService.deleteDepartment(department.getId());
        verify(departmentRepository,times(1)).delete(department.getId());
        
}
}
