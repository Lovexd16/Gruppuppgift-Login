package com.login.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.login.login.model.User;
import com.login.login.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register-account")
    public String newmember(@RequestBody User user, RedirectAttributes r) {
        // if (!user.equals(null)) {
        userRepository.save(user);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        r.addFlashAttribute("registred", "Registrerad!");

        return "redirect:/";
    }

    // r.addFlashAttribute("errorMsgRegister", "Du måste fylla i alla fält!");
    // return "redirect:/register";
    // }
}
