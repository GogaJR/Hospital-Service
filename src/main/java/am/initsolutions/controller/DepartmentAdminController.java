package am.initsolutions.controller;

import am.initsolutions.forms.DepartmentForm;
import am.initsolutions.models.Department;
import am.initsolutions.models.Hospital;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.repository.DoctorRepository;

import am.initsolutions.services.DepartmentService;
import am.initsolutions.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Controller
public class DepartmentAdminController {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private  DepartmentRepository departmentRepository;
    //SELECT DEPARTMENT
    @GetMapping("/departmentAdmin")
    public String departmentAdmin(Model map, @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse( 5);
        Page<Department> all = departmentRepository.findAll(new PageRequest(currentPage-1,pageSize));
       // List<Department> all = departmentService.getAll();
        map.addAttribute("departmentList", all);

        int totalPages = all.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            map.addAttribute("pageNumbers", pageNumbers);
        }
        return "departmentAdmin";

    }

    @GetMapping("/addDepartment")
    public String addDepartment( Model map) {
        List<Hospital> all = hospitalService.getAll();
        map.addAttribute("hospitaltList", all);
        return "addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(DepartmentForm departmentForm, ModelMap map) {
        Department addDep=departmentService.add(departmentForm);
        if (addDep != null) {
            return "redirect:/departmentAdmin";
        }

        map.addAttribute("error", true);
        return "redirect:/addDepartment";
    }

    //delete department
    @GetMapping("/delete")
    public String deleteDepartment(@RequestParam("id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departmentAdmin";
    }


    @GetMapping("/byDepartment")
    public String byDepartment(@RequestParam("departmentId") Long departmentId, ModelMap modelMap) {
        modelMap.addAttribute("doctors", doctorRepository.findAllByDepartmentId(departmentId));
        return "doctorsByDepartment";
    }


    @GetMapping("/editDepartment")
    public String editDepartment(@RequestParam("id") Long id, ModelMap model){
        Department department = departmentService.get(id);
        model.addAttribute("department", department);
//        List<Hospital> all = hospitalService.getAll();
//        model.addAttribute("hospitaltList", all);
        return "editDepartment";
    }



    @PostMapping("/editDepartment")
    public String editPharmacy(Department department) {
        departmentService.update(department);
        return "redirect:/departmentAdmin";
    }


}
