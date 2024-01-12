package com.login.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.login.login.model.User;
import com.login.login.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register-account")
    public String newmember(@ModelAttribute User user, RedirectAttributes r) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                r.addFlashAttribute("error", "En användare med denna e-postadress finns redan.");
                return "redirect:/register";
            }

            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                r.addFlashAttribute("error", "En användare med detta användarnamn finns redan.");
                return "redirect:/register";
            }

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Ett fel inträffade när vi försökte skapa ditt konto: ", e);
            r.addFlashAttribute("error", "Ett fel uppstod när vi försökte skapa ditt konto. Försök igen senare.");
            return "redirect:/register";
        }

        r.addFlashAttribute("registered", "Registrerad!");

        log.info("Du registrerade ett konto med användarnamnet " + user.getUsername());

        return "redirect:/";
    }
}