package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.service.DrzavaService;
import hr.fer.oap.service.KorisnikService;
import hr.fer.oap.service.MjestoService;
import hr.fer.oap.service.OglasService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("oglas")
public class OglasController {
    private final MjestoService mjestoService;
    private final DrzavaService drzavaService;
    private final OglasService oglasService;
    private final KorisnikService korisnikService;

    public OglasController(MjestoService mjestoService, DrzavaService drzavaService,
                           OglasService oglasService, KorisnikService korisnikService) {
        this.mjestoService = mjestoService;
        this.drzavaService = drzavaService;
        this.oglasService = oglasService;
        this.korisnikService = korisnikService;
    }

    @GetMapping("/{id}")
    String index(@PathVariable("id") Long oglasId, Model model) {
        model.addAttribute("oglasId", oglasId);
        return "oglas";
    }

    @GetMapping("/stvori")
    String stvoriOglasForm(@AuthenticationPrincipal UserDetails userDetails, Model model,
                           @RequestParam(name = "odabranaDrzavaOznaka", required = false) String odabranaDrzavaOznaka) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("drzave", this.drzavaService.fetchAll());
        model.addAttribute("odabranaDrzavaOznaka", odabranaDrzavaOznaka);
        model.addAttribute("mjesta", mjestoService.fetchByDrzavaOznaka(odabranaDrzavaOznaka));
        return "stvoriOglas";
    }

    @Validated
    @PostMapping("/stvori")
    String stvoriOglas(@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute CreateOglasDTO dto) {
        var mjesto = mjestoService.fetchById(dto.getMjesto()).get();
        var korisnik = korisnikService.fetchByUsername(userDetails.getUsername()).get();
        var oglas = oglasService.createOglas(dto, mjesto, korisnik);
        return "redirect:/oglas/" + oglas.getId();
    }
}
