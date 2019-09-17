package am.initsolutions.controller;

import am.initsolutions.dto.PatientDto;
import am.initsolutions.models.Hospital;
import am.initsolutions.models.Patient;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.repository.HospitalRepository;
import am.initsolutions.repository.UserRepository;
import am.initsolutions.security.SpringUser;
import am.initsolutions.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/mainAdmin")
    public String admin(Model map) {
        List<Hospital> all = hospitalRepository.findAll();
        map.addAttribute("hospitalList", all);
        return "mainAdmin";
    }
    
    @GetMapping("/patient")
    public String patient(@ModelAttribute("userId") Long userId, ModelMap modelMap){
        Patient patient = patientService.getByUserId(userId);
        PatientDto patientDto = PatientDto.from(patient);
        modelMap.addAttribute("patient", patientDto);

        return "patient";
    }

    @GetMapping("/loginSuccess")
    public RedirectView loginSuccess(@AuthenticationPrincipal
                                       SpringUser springUser, RedirectAttributes attributes) {
        if (springUser.getUser().getUserType().equals(UserType.ADMIN)) {
            return new RedirectView("/mainAdmin");
        }else if(springUser.getUser().getUserType().equals(UserType.HOSPITAL_ADMIN)){
            return new RedirectView("/departmentAdmin");
        }else if(springUser.getUser().getUserType().equals(UserType.PHARMACY_ADMIN)){
            return new RedirectView("/pharmacyAdmin");
        } else if (springUser.getUser().getUserType().equals(UserType.DOCTOR)) {
            return new RedirectView("/doctor");
        }else {
            Long userId = springUser.getUser().getId();
            attributes.addFlashAttribute("userId", userId);

            return new RedirectView("/patient");
        }


    }
}
