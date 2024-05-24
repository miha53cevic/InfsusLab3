package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateEditOglasDTO;
import hr.fer.oap.mapping.MappingToOglasDuration;
import hr.fer.oap.service.*;
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
    private final KategorijaService kategorijaService;
    private final PripadaKategorijiService pripadaKategorijiService;

    public OglasController(MjestoService mjestoService, DrzavaService drzavaService,
                           OglasService oglasService, KorisnikService korisnikService,
                           KategorijaService kategorijaService, PripadaKategorijiService pripadaKategorijiService) {
        this.mjestoService = mjestoService;
        this.drzavaService = drzavaService;
        this.oglasService = oglasService;
        this.korisnikService = korisnikService;
        this.kategorijaService = kategorijaService;
        this.pripadaKategorijiService = pripadaKategorijiService;
    }

    @GetMapping("/{id}")
    String index(@AuthenticationPrincipal UserDetails userDetails,
                 @PathVariable("id") Long oglasId, Model model) {
        var oglas = oglasService.fetchById(oglasId).get();
        Long hoursLeft = MappingToOglasDuration.oglasToDuration(oglas);
        var mjesto = oglas.getMjesto().getNaziv();
        var drzava = oglas.getMjesto().getDrzava().getNaziv();


        model.addAttribute("oglas", oglas);
        model.addAttribute("oglasId", oglasId);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("hoursLeft", hoursLeft);
        model.addAttribute("mjesto", mjesto);
        model.addAttribute("drzava", drzava);
        return "oglas/prikaz";
    }

    @GetMapping("/{id}/uredi")
    String editForm(@AuthenticationPrincipal UserDetails userDetails, Model model,
                @RequestParam(name = "odabranaDrzavaOznaka", required = false) String odabranaDrzavaOznaka,
                @PathVariable("id") Long oglasId) {
        var oglas = oglasService.fetchById(oglasId).get();
        if (odabranaDrzavaOznaka == null) {
            odabranaDrzavaOznaka = oglas.getMjesto().getDrzava().getOznaka();
        }
        var oglasKategorije = kategorijaService.findAllByOglas(oglas);
        model.addAttribute("oglas", oglas);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("drzave", this.drzavaService.fetchAll());
        model.addAttribute("odabranaDrzavaOznaka", odabranaDrzavaOznaka);
        model.addAttribute("mjesta", mjestoService.fetchByDrzavaOznaka(odabranaDrzavaOznaka));
        model.addAttribute("oglasKategorije", oglasKategorije);
        return "oglas/edit";
    }

    @Validated
    @PostMapping("/{id}/uredi")
    String edit(@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute CreateEditOglasDTO dto) {
        var korisnik = korisnikService.fetchByUsername(userDetails.getUsername()).get();
        var oglas = oglasService.createOglas(dto, korisnik);
        return "redirect:/moji-oglasi";
    }

    @GetMapping("/stvori")
    String stvoriOglasForm(@AuthenticationPrincipal UserDetails userDetails, Model model,
                           @RequestParam(name = "odabranaDrzavaOznaka", required = false) String odabranaDrzavaOznaka) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("drzave", this.drzavaService.fetchAll());
        model.addAttribute("odabranaDrzavaOznaka", odabranaDrzavaOznaka);
        model.addAttribute("mjesta", mjestoService.fetchByDrzavaOznaka(odabranaDrzavaOznaka));
        model.addAttribute("kategorije", kategorijaService.fetchAll());
        return "oglas/stvori";
    }

    @Validated
    @PostMapping("/stvori")
    String stvoriOglas(@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute CreateEditOglasDTO dto) {
        var korisnik = korisnikService.fetchByUsername(userDetails.getUsername()).get();
        var oglas = oglasService.createOglas(dto, korisnik);
        dto.getKategorije().forEach(kategorijaId -> {
            var kategorija = kategorijaService.findById(kategorijaId).get();
            pripadaKategorijiService.addKategorijaToOglas(kategorija, oglas);
        });
        return "redirect:/oglas/" + oglas.getId();
    }
}
