package hr.fer.oap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("")
public class OglasnikController {
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("page", "oglasnik");
        return "oglasnik";
    }
}