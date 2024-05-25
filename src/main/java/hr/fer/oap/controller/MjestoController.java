package hr.fer.oap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mjesto")
public class MjestoController {
    String index() {
        return "mjesto";
    }
}
