package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.dao.dto.EditOglasDTO;
import hr.fer.oap.mapping.MappingToOglasDuration;
import hr.fer.oap.service.*;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    public OglasController(MjestoService mjestoService,
                           DrzavaService drzavaService,
                           OglasService oglasService,
                           KorisnikService korisnikService,
                           KategorijaService kategorijaService,
                           PripadaKategorijiService pripadaKategorijiService
    ) {
        this.mjestoService = mjestoService;
        this.drzavaService = drzavaService;
        this.oglasService = oglasService;
        this.korisnikService = korisnikService;
        this.kategorijaService = kategorijaService;
        this.pripadaKategorijiService = pripadaKategorijiService;
    }

    @GetMapping("/{id}")
    String index(@AuthenticationPrincipal UserDetails userDetails,
                 @PathVariable("id") Long oglasId,
                 Model model
    ) {
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
    String editForm(@AuthenticationPrincipal UserDetails userDetails,
                    Model model,
                    @RequestParam(name = "odabranaDrzavaOznaka", required = false) String odabranaDrzavaOznaka,
                    @PathVariable("id") Long oglasId
    ) {
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
        model.addAttribute("kategorije", kategorijaService.fetchAll());
        return "oglas/edit";
    }

    @PostMapping("/{id}/uredi")
    String edit(
            @Valid EditOglasDTO dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        oglasService.editOglas(dto);
        return "redirect:/moji-oglasi";
    }

    @GetMapping("/stvori")
    String stvoriOglasForm(@AuthenticationPrincipal UserDetails userDetails,
                           Model model,
                           @RequestParam(name = "odabranaDrzavaOznaka", required = false) String odabranaDrzavaOznaka,
                           CreateOglasDTO dto
    ) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("drzave", this.drzavaService.fetchAll());
        model.addAttribute("odabranaDrzavaOznaka", odabranaDrzavaOznaka);
        model.addAttribute("mjesta", mjestoService.fetchByDrzavaOznaka(odabranaDrzavaOznaka));
        model.addAttribute("kategorije", kategorijaService.fetchAll());
        model.addAttribute("dto", dto);
        return "oglas/stvori";
    }

    @PostMapping("/stvori")
    String stvoriOglas(@Valid CreateOglasDTO dto,
                       BindingResult bindingResult,
                       Model model,
                       @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (dto.pocetnoVrijeme() != null && dto.zavrsnoVrijeme() != null) {
            if (!dto.zavrsnoVrijeme().isAfter(dto.pocetnoVrijeme())) {
                bindingResult.addError(new FieldError("dto", "zavrsnoVrijeme", "ZavrsnoVrijeme mora biti nakon početnog!"));
            }
        }
        if (bindingResult.hasErrors()) {
            var mjesto = this.mjestoService.fetchById(dto.mjesto()).get();
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("drzave", this.drzavaService.fetchAll());
            model.addAttribute("odabranaDrzavaOznaka", mjesto.getDrzava().getOznaka());
            model.addAttribute("mjesta", mjestoService.fetchByDrzavaOznaka(mjesto.getDrzava().getOznaka()));
            model.addAttribute("kategorije", kategorijaService.fetchAll());
            model.addAttribute("dto", dto);
            return "oglas/stvori";
        }
        var korisnik = korisnikService.fetchByUsername(userDetails.getUsername()).get();
        var oglas = oglasService.createOglas(dto, korisnik);
        dto.kategorije().forEach(kategorijaId -> {
            var kategorija = kategorijaService.findById(kategorijaId).get();
            pripadaKategorijiService.addKategorijaToOglas(kategorija, oglas);
        });
        return "redirect:/oglas/" + oglas.getId();
    }
}
