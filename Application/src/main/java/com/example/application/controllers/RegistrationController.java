package com.example.application.controllers;

import com.example.application.Entities.User;
import com.example.application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpRequest;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    private final ModelAndView modelAndView = new ModelAndView();
    @GetMapping("/registration")
    @ResponseBody
    public ModelAndView registration(){
        modelAndView.setViewName("registration.html");
        return modelAndView;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ModelAndView addNewUser(User user, Map<String, Object> model){
//        user.setRole(Collections.singleton(Role.CLIENT));
        User userFromDB= userRepository.findByUsername(user.getUsername());
        if(userFromDB != null){
        model.put("message", "This name is reserved. Please try again");
            modelAndView.setViewName("registration.html");
        }else {
            userRepository.save(user);
            modelAndView.setViewName("login.html");
        }
        return modelAndView;
    }
}
