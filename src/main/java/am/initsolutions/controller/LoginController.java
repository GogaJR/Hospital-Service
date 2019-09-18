package am.initsolutions.controller;

import am.initsolutions.models.Doctor;
import am.initsolutions.models.Hospital;
import am.initsolutions.models.enums.UserType;
import am.initsolutions.security.SpringUser;
import am.initsolutions.services.DoctorService;
import am.initsolutions.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/mainAdmin")
    public String admin(Model map) {
        List<Hospital> all = hospitalService.getAll();
        map.addAttribute("hospitalList", all);
        return "mainAdmin";
    }
    
    @GetMapping("/patient")
    public String patient(){

        return "patient";
    }
    
    @GetMapping("/doctorPage")
    public String doctorPage(@ModelAttribute("currentUser") Doctor doctor, ModelMap modelMap){
        modelMap.addAttribute("user", doctor);
        return "doctorPage";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal
                                       SpringUser springUser, RedirectAttributes redirectAttributes,
                               Model model, HttpServletRequest request) {
        if (springUser.getUser().getUserType().equals(UserType.ADMIN)) {
            return "redirect:/mainAdmin";
        }else if(springUser.getUser().getUserType().equals(UserType.HOSPITAL_ADMIN)){
            return "redirect:/departmentAdmin";
        }else if(springUser.getUser().getUserType().equals(UserType.PHARMACY_ADMIN)){
            return "redirect:/pharmacyAdmin";
        } else if (springUser.getUser().getUserType().equals(UserType.DOCTOR)) {
            long userId=springUser.getUser().getId();
            Doctor user=doctorService.getByUserId(userId);
            request.getSession().setAttribute("user",user);
            if (user == null) {
                return "redirect:/login";
            }
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/doctorPage";
        }else {
            return "redirect:/patient";
        }


    }
}
