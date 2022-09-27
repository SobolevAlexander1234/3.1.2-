package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.MyUser;
import web.service.RoleServiceImpl;
import web.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("allUsers", userServiceImpl.allUsers());
        return "home";
    }

    @PostMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new MyUser());
        model.addAttribute("roles", roleServiceImpl.getRoleList());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") MyUser myUser) {
        userServiceImpl.addUser(myUser);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("roles", roleServiceImpl.getRoleList());
        return "edit";
    }

    @PatchMapping("{id}")
    public String updateUserById(@ModelAttribute("user") MyUser myUser, @PathVariable("id") Long id) {
        userServiceImpl.updateUser(myUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/admin";
    }
}