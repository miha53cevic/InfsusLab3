package hr.fer.oap.controller;

import hr.fer.oap.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    private MjestoService mjestoService;

    @Autowired
    public GreetingController(MjestoService mjestoService) {
        this.mjestoService = mjestoService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("mjesta", mjestoService.fetchAll());
        return "greeting";
    }
}