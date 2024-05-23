package hr.fer.oap.controller;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.mapping.MappingToFilteredOglasi;
import hr.fer.oap.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("")
public class OglasnikController {
    private final OglasService oglasService;
    private final KategorijaService kategorijaService;

    public OglasnikController(OglasService oglasService, KategorijaService kategorijaService) {
        this.oglasService = oglasService;
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("page", "oglasnik");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasService.fetchAll());
        model.addAttribute("kategorije", kategorijaService.fetchAll());
        return "oglasnik";
    }

    @GetMapping("/oglasnik")
    public String dohvatiOglaseForm(@AuthenticationPrincipal UserDetails userDetails, Model model,
                                    @RequestParam(name = "searchNaziv", required = false) String naziv,
                                    @RequestParam(name = "kategorija", required = false) Long searchKategorija,
                                    @RequestParam(name = "istekli", required = false) String istekli) {
        var oglasi = oglasService.fetchAll();;
        if (searchKategorija != null) {
            Kategorija kat = kategorijaService.findById(searchKategorija).get();
            oglasi = oglasService.findAllByKategorija(kat);
        }

        if (naziv != null) {
            oglasi = MappingToFilteredOglasi.fromOglasiByNaziv(oglasi, naziv);
        }
        var kategorije = kategorijaService.fetchAll();
        model.addAttribute("page", "oglasnik");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("kategorije", kategorije);
        return "oglasnik";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}