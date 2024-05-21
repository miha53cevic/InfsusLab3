package hr.fer.oap.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("")
public class OglasnikController {
    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("page", "oglasnik");
        model.addAttribute("username", userDetails.getUsername());
        return "oglasnik";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}