package com.example.CrudAppSecurBoor.controllers;


import com.example.CrudAppSecurBoor.models.Role;
import com.example.CrudAppSecurBoor.models.User;
import com.example.CrudAppSecurBoor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "Log/login";
    }

    @PostMapping("/login/create")
    public String create(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
            roles.add(new Role(2L, "ROLE_USER"));
        user.setRoles(roles);
        userService.save(user);

        return "redirect:/login";
    }//done


    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {

        return "Log/registration";
    }//done

}
