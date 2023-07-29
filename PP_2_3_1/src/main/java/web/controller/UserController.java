package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers());
        return "/userList";
    }

    @GetMapping("/new")
    public String getAddUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String getUpdate(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(ModelMap modelMap, @PathVariable("id") int id) {
        userService.delete(id);
        modelMap.addAttribute("listUser", userService.getAllUsers());
        return "redirect:/";
    }
}
