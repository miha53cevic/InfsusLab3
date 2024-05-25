package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreatePripadaKategorijiDTO;
import hr.fer.oap.dao.dto.DeletePripadaKategorijiDTO;
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
            @Valid CreatePripadaKategorijiDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        return pripadaKategorijiService.create(dto).toString();
    }

    @PostMapping("/delete")
    public String delete(
            @Valid DeletePripadaKategorijiDTO dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        pripadaKategorijiService.delete(dto);
        return "OK";
    }
}
