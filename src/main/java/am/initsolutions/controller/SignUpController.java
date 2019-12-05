package am.initsolutions.controller;

import am.initsolutions.forms.PatientForm;
import am.initsolutions.models.Patient;
import am.initsolutions.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String getSignUpPage(@ModelAttribute("exists") Boolean exists,
                                HttpServletRequest request, ModelMap modelMap) {
        if (exists != null) {
            modelMap.addAttribute("exists", exists);
            request.getSession().setAttribute("exists", false);
        }

        return "signUp";
    }

    @PostMapping("/register")
    public String signUp(PatientForm patientForm, HttpServletRequest request) {
        Patient newPatient = patientService.add(patientForm);
        if (newPatient != null) {
            return "redirect:/login";
        }

        request.getSession().setAttribute("exists", true);
        return "redirect:/register";
    }
}
