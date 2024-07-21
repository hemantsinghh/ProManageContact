package com.promanagecontact.controllers;

import com.promanagecontact.entities.User;
import com.promanagecontact.form.LoginForm;
import com.promanagecontact.form.RegisterForm;
import com.promanagecontact.helper.DisplayMessage;
import com.promanagecontact.helper.Message;
import com.promanagecontact.services.Implementaion.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/register")
    public String signUp(Model model) {
        RegisterForm registerForm =  new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerSubmit(@Valid @ModelAttribute RegisterForm registerForm, BindingResult br, HttpSession httpSession){
        User user =  new User();
        if(br.hasErrors()) return "register";
        user.setName(registerForm.getName());
        user.setAbout(registerForm.getAbout());
        user.setEmail(registerForm.getEmail());
        user.setPhoneNumber(registerForm.getPhoneNumber());
        user.setPassword(registerForm.getPassword());
        userService.saveUser(user);
        Message message = new Message("Registration Successful",DisplayMessage.green);
        httpSession.setAttribute("message",message);
        return "redirect:/register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping(value = "/authenticate")
    public String processLogin(@ModelAttribute LoginForm loginForm){
        String email =  loginForm.getEmail();
        String password =  loginForm.getPassword();
        if(email!=null && userService.userExistByEmail(email) && password!=null){
            Boolean bool = userService.IfPasswordMatches(email, loginForm.getPassword());
            if (bool) return "redirect:/user/dashboard";
        }
        return "redirect:/login";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

}
