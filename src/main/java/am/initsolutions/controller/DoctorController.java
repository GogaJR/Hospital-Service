package am.initsolutions.controller;

import am.initsolutions.models.Doctor;
import am.initsolutions.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/patientListByDoctor")
    public String patientListByDoctor(@RequestParam("id") Long id, ModelMap model){
        Doctor doctor=doctorService.get(id);
        model.addAttribute("doctor",doctor);
        return "patientListByDoctor";
    }
}
