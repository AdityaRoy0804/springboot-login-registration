package com.example.App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {

    private final UserService userService;

    public testController(UserService userService) {
        this.userService = userService;
    }

    // STEP 1: Registration page (FIRST PAGE)
    @GetMapping("/")
    public String registerPage() {
        return "register";
    }

    // STEP 2: Handle registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password) {

        if (userService.userExists(username)) {
            return "register-error";
        }

        userService.saveUser(username, password);
        return "success"; // registration success page
    }

    // STEP 3: Login page (login.html)
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // ✅ login.html
    }

    // STEP 4: Handle login
    @PostMapping("/submit")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        if (!userService.validateLogin(username, password)) {
            return "login-error";
        }

        return "home"; // home.html
    }
}