package am.initsolutions.controller;

import am.initsolutions.forms.DoctorForm;
import am.initsolutions.models.Department;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Hospital;
import am.initsolutions.models.User;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.repository.DepartmentRepository;
import am.initsolutions.repository.DoctorRepository;
import am.initsolutions.repository.HospitalRepository;
import am.initsolutions.repository.UserRepository;
import am.initsolutions.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class DoctorController {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam("id") Long id) {
        Optional<Doctor> d = Optional.ofNullable(doctorRepository.findOne(id));
        if (d.isPresent()) {
            doctorRepository.delete(id);
        }
        return "redirect:/departmentAdmin";
    }

    @GetMapping("/addDoctor")
    public String addDepartment( Model map) {
        List<Hospital> all = hospitalRepository.findAll();
        List<Department> dep = departmentRepository.findAll();
        map.addAttribute("hospitaltList", all);
        map.addAttribute("departmentList", dep);
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String addDepartment(DoctorForm doctorForm, ModelMap modelMap) {
        Doctor doctorNew = doctorService.add(doctorForm);
        if (doctorNew != null) {
            return "redirect:/departmentAdmin";
        }
        modelMap.addAttribute("error", true);
        return "addDoctor";
    }
}
