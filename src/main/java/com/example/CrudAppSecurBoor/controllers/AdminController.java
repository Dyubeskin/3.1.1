package com.example.CrudAppSecurBoor.controllers;

import com.example.CrudAppSecurBoor.models.Role;
import com.example.CrudAppSecurBoor.models.User;
import com.example.CrudAppSecurBoor.service.RoleServiceImpl;
import com.example.CrudAppSecurBoor.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admins/")
public class AdminController {

    private final UserService userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String ShowAllUsers(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.index());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admins/index";
    } //done

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admins/show";
    }//done

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admins/new";
    }//done

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roless", required = false) String [] role
//                         @RequestParam(value = "ROLE_ADMIN", required = false) boolean admin,
//                         @RequestParam(value = "ROLE_USER", required = false) boolean userr
    ) {
//        Set<Role> roles = new HashSet<>();
//        if (userr) {
//            roles.add(new Role(2L, "ROLE_USER"));
//        }
//        if (admin) {
//            roles.add(new Role(1L, "ROLE_ADMIN"));
//            roles.add(new Role(2L, "ROLE_USER"));
//        }
//        user.setRoles(roles);

        Set<Role> roles = new HashSet<>();
        for (String roleStr : role) {
            roles.add(roleService.getRoleByName(roleStr));
        }
        user.setRoles(roles);

        userService.save(user);


        return "redirect:/admins/";
    }//done

    @GetMapping("/users/{id}/edit")
    public String edit( @PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));

        return "admins/edit";
    }//done

    @PostMapping("/users-update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id,
                         @RequestParam(value = "admin", required = false) boolean admin,
                         @RequestParam(value = "userr", required = false) boolean userr) {

        Set<Role> roles = new HashSet<>();
        if (userr) {
            roles.add(new Role(2L, "ROLE_USER"));
        }
        if (admin) {
            roles.add(new Role(1L, "ROLE_ADMIN"));
            roles.add(new Role(2L, "ROLE_USER"));
        }
        user.setRoles(roles);
        userService.update(id, user);
        return "redirect:/admins/";
    }


    @GetMapping("/users-delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admins/";
    }

}
