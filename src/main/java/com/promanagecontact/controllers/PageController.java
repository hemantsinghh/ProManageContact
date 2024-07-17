package com.promanagecontact.controllers;

import com.promanagecontact.entities.User;
import com.promanagecontact.form.RegisterForm;
import com.promanagecontact.services.Implementaion.UserServiceImpl;
import com.promanagecontact.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PageController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("name","Hemant Singh");
        return "home";
    }
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    @RequestMapping("/services")
    public String services(){
        return "services";
    }
    @RequestMapping("/login")
    public String login() {
        return new String("login");
    }
    @GetMapping("/register")
    public String signUp(Model model) {
        RegisterForm registerForm =  new RegisterForm();
        registerForm.setName("Hemant");
        model.addAttribute("registerForm", registerForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute RegisterForm registerForm){
        User user = User.builder()
                        .name(registerForm.getName())
                                .about(registerForm.getAbout())
                                        .email(registerForm.getEmail())
                                                .password(registerForm.getPassword())
                                                        .build();
        userService.saveUser(user);
        return "redirect:/register";
    }
    @RequestMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    
    
}
