package hr.fer.oap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("moji-oglasi")
public class MojiOglasiController {
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("page", "moji-oglasi");
        return "mojiOglasi";
    }
}
