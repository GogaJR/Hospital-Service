package am.initsolutions.controller;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Patient;
import am.initsolutions.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/register")
    public String signUp(PatientForm patientForm, ModelMap modelMap) {
        Patient newPatient = patientService.add(patientForm);
        if (newPatient != null) {
            return "redirect:/login";
        }

        modelMap.addAttribute("error", true);
        return "redirect:/register";
    }
}
