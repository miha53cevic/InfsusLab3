package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateEditMjestoDTO;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.mapping.MappingToFilteredMjesta;
import hr.fer.oap.service.DrzavaService;
import hr.fer.oap.service.MjestoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mjesto")
public class MjestoController {
    private MjestoService mjestoService;
    private DrzavaService drzavaService;

    @Autowired
    public MjestoController(
            MjestoService mjestoService,
            DrzavaService drzavaService
    ) {
        this.mjestoService = mjestoService;
        this.drzavaService = drzavaService;
    }

    @RequestMapping("/crud")
    String index(
            Model model,
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(value = "startRecord", defaultValue = "0") int startRecord,
            @RequestParam(value = "numberOfRecords", defaultValue = "200") int numberOfRecords,
            @RequestParam(value = "searchNaziv", required = false) String searchNaziv,
            @RequestParam(value = "searchDrzava", required = false) String searchDrzava

    ) {
        List<Mjesto> mjesta = mjestoService.fetchAll();
        if (searchNaziv != null) {
            mjesta = MappingToFilteredMjesta.fromMjestaByNaziv(mjesta, searchNaziv);
        }
        if (searchDrzava != null) {
            mjesta = MappingToFilteredMjesta.fromMjestaByDrzavaOznaka(mjesta, searchDrzava);
        }
        mjesta = mjesta.subList(startRecord, Math.min(numberOfRecords, mjesta.size()));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("page", "mjesto/crud");
        model.addAttribute("mjesta", mjesta);
        model.addAttribute("drzave", drzavaService.fetchAll());
        model.addAttribute("searchNaziv", searchNaziv);
        model.addAttribute("searchDrzava", searchDrzava);
        return "mjesto/crud";
    }

    @PostMapping("/{id}/delete")
    String delete(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long mjestoId
    ) {
        mjestoService.deleteById(mjestoId);
        return "redirect:/mjesto/crud";
    }

    @GetMapping("/add")
    String addForm(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model,
            CreateEditMjestoDTO dto
    ) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("dto", dto);
        model.addAttribute("drzave", drzavaService.fetchAll());
        return "mjesto/add";
    }

    @PostMapping("/add")
    String add(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid CreateEditMjestoDTO dto
    ) {
        mjestoService.createMjesto(dto);
        return "redirect:/mjesto/crud";
    }

    @GetMapping("/{id}/uredi")
    String edit(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long mjestoId,
            Model model
    ) {
        var mjesto = this.mjestoService.fetchById(mjestoId).get();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("mjesto", mjesto);
        model.addAttribute("drzave", drzavaService.fetchAll());
        return "mjesto/edit";
    }

    @PostMapping("/{id}/uredi")
    String add(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long mjestoId,
            @Valid Mjesto mjesto
    ) {
        mjestoService.editMjesto(mjesto);
        return "redirect:/mjesto/crud";
    }
}
