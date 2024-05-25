package hr.fer.oap.controller;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.mapping.MappingToFilteredOglasi;
import hr.fer.oap.mapping.MappingToOglasDuration;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.OglasService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        var oglasi = oglasService.fetchAll();
        List<Long> hoursLeftList = oglasi.stream().map(MappingToOglasDuration::oglasToDuration).toList();

        model.addAttribute("page", "oglasnik");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("kategorije", kategorijaService.fetchAll());
        model.addAttribute("hoursLeftList", hoursLeftList);
        return "oglasnik";
    }

    @GetMapping("/oglasnik")
    public String dohvatiOglaseForm(@AuthenticationPrincipal UserDetails userDetails, Model model,
                                    @RequestParam(name = "searchInput", required = false) String naziv,
                                    @RequestParam(name = "kategorija", required = false) Long searchKategorija,
                                    @RequestParam(name = "istekli", required = false) String istekli) {
        var oglasi = oglasService.fetchAll();
        if (searchKategorija != null) {
            Kategorija kat = kategorijaService.findById(searchKategorija).get();
            oglasi = oglasService.findAllByKategorija(kat);
        }

        if (naziv != null) {
            oglasi = MappingToFilteredOglasi.fromOglasiByNaziv(oglasi, naziv);
        }

        if (istekli != null && !istekli.equals("0")) {
            if (istekli.equals("1")) {
                oglasi = MappingToFilteredOglasi.fromOglasiByTime(oglasi, true);
            } else {
                oglasi = MappingToFilteredOglasi.fromOglasiByTime(oglasi, false);
            }
        }
        var kategorije = kategorijaService.fetchAll();
        List<Long> hoursLeftList = oglasi.stream().map(MappingToOglasDuration::oglasToDuration).toList();
        model.addAttribute("page", "oglasnik");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("kategorije", kategorije);
        model.addAttribute("hoursLeftList", hoursLeftList);
        model.addAttribute("searchNaziv", naziv);
        model.addAttribute("searchKategorija", searchKategorija);
        model.addAttribute("istekli", istekli);
        return "oglasnik";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}