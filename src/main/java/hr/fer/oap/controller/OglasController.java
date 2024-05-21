package hr.fer.oap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("oglas")
public class OglasController {
    @GetMapping("/{id}")
    String index(@PathVariable("id") Long oglasId, Model model) {
        model.addAttribute("oglasId", oglasId);
        return "oglas";
    }
}
