package am.initsolutions.controller;

import am.initsolutions.models.Department;
import am.initsolutions.models.Hospital;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.HospitalRepository;
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
    private DepartmentRepository departmentRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @GetMapping("/departmentAdmin")
    public String departmentAdmin(Model map){
        List<Department> all = departmentRepository.findAll();
        map.addAttribute("departmentList", all);
        return "departmentAdmin";

    }

    @GetMapping("/addDepartment")
    public String addDepartment( Model map) {
        List<Hospital> all = hospitalRepository.findAll();
        map.addAttribute("hospitaltList", all);
        return "addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute Department department,ModelMap map) {
        departmentRepository .save(department);
        map.addAttribute("error", true);
        return "redirect:/departmentAdmin";
    }


    @GetMapping("/delete")
    public String deleteDepartment(@RequestParam("id") Long id) {
        Optional<Department> d = Optional.ofNullable(departmentRepository.findOne(id));
        if (d.isPresent()) {
            departmentRepository.delete(id);
        }
        return "redirect:/departmentAdmin";
    }

    @GetMapping("/byDepartment")
    public String byDepartment(@RequestParam("departmentId") Long departmentId, ModelMap modelMap) {
        modelMap.addAttribute("doctors", doctorRepository.findAllByDepartmentId(departmentId));
        return "doctorsByDepartment";
    }

    @GetMapping("/editDepartment")
    public String editDepartment(@RequestParam("id") Long id, ModelMap model){
        Optional<Department> dId = Optional.ofNullable(departmentRepository.findOne(id));
        if (dId.isPresent()) {
            model.addAttribute("department", dId);
            List<Hospital> all = hospitalRepository.findAll();
            model.addAttribute("hospitaltList", all);
        }
        return "editDepartment";
    }


}
