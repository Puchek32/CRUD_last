package testgroup.BOOT_prilozhenie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import testgroup.BOOT_prilozhenie.model.Role;
import testgroup.BOOT_prilozhenie.model.User;
import testgroup.BOOT_prilozhenie.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String allUsers(ModelMap modelMap) {
        modelMap.addAttribute("usersList", userService.allUsers());
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        modelMap.addAttribute("userPage", user);
        modelMap.addAttribute("AorU", user.getUserRole().contains("ADMIN"));
        return "123";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editPage(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("userEdit", userService.getById(id));
        return "123";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false, name = "ADMIN") String ADMIN,
                           @RequestParam(required = false, name = "USER") String USER) {
        user.setRoles(kysochek(ADMIN, USER));
        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/add")
    public String addPage(ModelMap modelMap) {
        modelMap.addAttribute("userAdd", new User());
        return "123";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(required = false, name = "ADMIN") String ADMIN,
                          @RequestParam(required = false, name = "USER") String USER) {
        user.setRoles(kysochek(ADMIN, USER));
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }


    @GetMapping(value = "/user")
    public String currentUser(ModelMap modelMap) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        modelMap.addAttribute("userPage", user);
        modelMap.addAttribute("AorU", user.getUserRole().contains("ADMIN"));
        return "123";
    }

    private static Set<Role> kysochek(String ADMIN, String USER) {
        Set<Role> roles = new HashSet<>();
        if (ADMIN != null) {
            roles.add(new Role(1, ADMIN));
        }
        if (USER != null) {
            roles.add(new Role(2, USER));
        }
        if (ADMIN == null && USER == null) {
            roles.add(new Role(2, USER));
        }
        return roles;
    }
}
