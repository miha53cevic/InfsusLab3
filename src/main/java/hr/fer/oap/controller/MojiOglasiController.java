package hr.fer.oap.controller;

import hr.fer.oap.domain.Oglas;
import hr.fer.oap.service.MjestoService;
import hr.fer.oap.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller()
@RequestMapping("moji-oglasi")
public class MojiOglasiController {
    private final OglasService oglasService;

    @Autowired
    public MojiOglasiController(OglasService oglasService) {
        this.oglasService = oglasService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("page", "moji-oglasi");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasService.fetchByKorisnikId((long)1));
        return "mojiOglasi";
    }
}
