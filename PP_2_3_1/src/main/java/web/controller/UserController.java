package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

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

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, ModelMap modelMap) {
//        modelMap.addAttribute("userId", userService.getUserById(id));
//        return "/userList";
//    }

    @GetMapping("/new")
public String getAddUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("new")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String getUpdate(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("user",userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(ModelMap modelMap, @PathVariable("id") int id) {
        userService.delete(id);
        modelMap.addAttribute("listUser", userService.getAllUsers());
        return "redirect:/";
    }
}
