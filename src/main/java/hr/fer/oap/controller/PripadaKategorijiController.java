package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateDeletePripadaKategorijiDTO;
import hr.fer.oap.dao.dto.EditPripadaKategorijiDTO;
import hr.fer.oap.service.PripadaKategorijiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pripadaKategoriji")
public class PripadaKategorijiController {
    private PripadaKategorijiService pripadaKategorijiService;

    @Autowired
    public PripadaKategorijiController(
            PripadaKategorijiService pripadaKategorijiService
    ) {
        this.pripadaKategorijiService = pripadaKategorijiService;
    }

    @PostMapping("/add")
    public String add(
            @Valid CreateDeletePripadaKategorijiDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        return pripadaKategorijiService.create(dto).toString();
    }

    @PostMapping("/edit")
    public String edit(
            @Valid EditPripadaKategorijiDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        return pripadaKategorijiService.edit(dto).toString();
    }

    @PostMapping("/delete")
    public String delete(
            @Valid CreateDeletePripadaKategorijiDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        pripadaKategorijiService.delete(dto);
        return "OK";
    }
}
