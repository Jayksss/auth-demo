package com.example.authdemo.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails,
                       Model model) {

        String email = userDetails != null ? userDetails.getUsername() : "손님";
        model.addAttribute("email", email);
        return "home";
    }
}
