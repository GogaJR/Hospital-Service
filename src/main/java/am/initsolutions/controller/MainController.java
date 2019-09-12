package am.initsolutions.controller;

import am.initsolutions.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main() {

        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "mainAdmin";
    }
}
