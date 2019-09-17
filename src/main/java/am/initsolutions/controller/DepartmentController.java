package am.initsolutions.controller;

import am.initsolutions.forms.DepartmentForm;
import am.initsolutions.models.Department;
import am.initsolutions.models.Hospital;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.HospitalRepository;
import am.initsolutions.services.DepartmentService;
import am.initsolutions.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class DepartmentController {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentService departmentService;
    //SELECT DEPARTMENT
    @GetMapping("/departmentAdmin")
    public String departmentAdmin(Model map){
        List<Department> all = departmentService.getAll();
        map.addAttribute("departmentList", all);
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
