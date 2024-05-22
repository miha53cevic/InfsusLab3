package hr.fer.oap.controller;

import hr.fer.oap.dao.repository.KorisnikRepository;
import hr.fer.oap.mapping.MappingToFilteredOglasi;
import hr.fer.oap.service.OglasService;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("moji-oglasi")
public class MojiOglasiController {
    private final OglasService oglasService;
    private final KorisnikService korisnikService;

    @Autowired
    public MojiOglasiController(OglasService oglasService, KorisnikService korisnikService) {
        this.oglasService = oglasService;
        this.korisnikService = korisnikService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model,
                        @RequestParam(value = "searchNaziv", required = false) String nazivOglasa) {
        var korisnik = korisnikService.fetchByUsername(userDetails.getUsername()).get();
        var oglasi = oglasService.fetchByKorisnikId(korisnik.getId());
        if (nazivOglasa != null) {
            oglasi = MappingToFilteredOglasi.fromOglasiByNaziv(oglasi, nazivOglasa);
        }
        model.addAttribute("page", "moji-oglasi");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("searchNaziv", nazivOglasa);
        return "mojiOglasi";
    }
}
