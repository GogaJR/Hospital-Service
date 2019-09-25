package am.initsolutions.controller;

import am.initsolutions.forms.DoctorForm;
import am.initsolutions.models.Department;
import am.initsolutions.models.Doctor;
import am.initsolutions.models.Hospital;
import am.initsolutions.services.DepartmentService;
import am.initsolutions.services.DoctorService;
import am.initsolutions.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class DoctorAdminController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;
   @Autowired
   private HospitalService hospitalService;
    @GetMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam("id") Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/departmentAdmin";
    }

    @GetMapping("/addDoctor")
    public String addDepartment( Model map) {
        List<Hospital> all = hospitalService.getAll();
        List<Department> dep = departmentService.getAll();
        map.addAttribute("hospitaltList", all);
        map.addAttribute("departmentList", dep);
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String addDepartment(DoctorForm doctorForm, ModelMap map) {
        Doctor doctorNew = doctorService.add(doctorForm);
        if (doctorNew != null) {
            return "redirect:/departmentAdmin";
        }
        map.addAttribute("error", true);
        return "addDoctor";
    }

//    @GetMapping("/editDoctor")
//    public String editDepartment(@RequestParam("id") Long id, ModelMap model){
//        Doctor doctor = doctorService.get(id);
//        model.addAttribute("doctor", doctor);
//        List<Department> departmentList = departmentService.getAll();
//        model.addAttribute("departmentList", departmentList);
//        List<Hospital> hospitaltList = hospitalService.getAll();
//        model.addAttribute("hospitaltList", hospitaltList);
//        return "editDoctor";
//    }
//
//
//
//    @PostMapping("/editDoctor")
//    public String editPharmacy(Department department) {
//        //doctorService.update(department);
//        return "redirect:/departmentAdmin";
//    }
}
