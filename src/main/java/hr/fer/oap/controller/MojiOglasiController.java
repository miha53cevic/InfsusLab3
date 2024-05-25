package hr.fer.oap.controller;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.mapping.MappingToFilteredOglasi;
import hr.fer.oap.mapping.MappingToOglasDuration;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.KorisnikService;
import hr.fer.oap.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
@RequestMapping("moji-oglasi")
public class MojiOglasiController {
    private final OglasService oglasService;
    private final KorisnikService korisnikService;
    private final KategorijaService kategorijaService;

    @Autowired
    public MojiOglasiController(
            OglasService oglasService,
            KorisnikService korisnikService,
            KategorijaService kategorijaService
    ) {
        this.oglasService = oglasService;
        this.korisnikService = korisnikService;
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model,
                        @RequestParam(value = "searchInput", required = false) String nazivOglasa,
                        @RequestParam(name = "kategorija", required = false) Long searchKategorija,
                        @RequestParam(name = "istekli", required = false) String istekli
    ) {
        var oglasi = oglasService.fetchAll();
        if (searchKategorija != null) {
            Kategorija kat = kategorijaService.findById(searchKategorija).get();
            oglasi = oglasService.findAllByKategorija(kat);
        }

        if (nazivOglasa != null) {
            oglasi = MappingToFilteredOglasi.fromOglasiByNaziv(oglasi, nazivOglasa);
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
        model.addAttribute("page", "moji-oglasi");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("kategorije", kategorije);
        model.addAttribute("hoursLeftList", hoursLeftList);
        model.addAttribute("searchNaziv", nazivOglasa);
        model.addAttribute("searchKategorija", searchKategorija);
        model.addAttribute("istekli", istekli);
        return "mojiOglasi";
    }
}
