package am.initsolutions.controller;

import am.initsolutions.models.Hospital;
import am.initsolutions.repository.HospitalRepository;
import am.initsolutions.repository.UserRepository;
import am.initsolutions.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalRepository hospitalRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/mainAdmin")
    public String admin(Model map) {
        List<Hospital> all = hospitalRepository.findAll();
        map.addAttribute("hospitalList", all);
        return "mainAdmin";
    }
    @GetMapping("/patient")
    public String patient(){
        return "patient";
    }
    @GetMapping("/hospitalAdmin")
    public String hospital(){
        return "hospitalAdmin";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal
                                       SpringUser springUser) {
        System.out.println(springUser.getUser().getUserType());
        if (springUser.getUser().getUserType().name().equals("ADMIN")) {
            return "redirect:/mainAdmin";
        }else if(springUser.getUser().getUserType().name().equals("HOSPITAL_ADMIN")){
            return "redirect:/hospitalAdmin";
        }else if(springUser.getUser().getUserType().name().equals("PHARMACY_ADMIN")){
            return "redirect:/pharmacyAdmin";
        } else if (springUser.getUser().getUserType().name().equals("DOCTOR")) {
            return "redirect:/doctor";
        }else {
            return "redirect:/patient";
        }


    }
}