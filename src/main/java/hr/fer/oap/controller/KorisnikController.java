package hr.fer.oap.controller;

import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
    private final PasswordEncoder passwordEncoder;
    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.korisnikService = korisnikService;
    }

    @GetMapping("/seed")
    public String korisnik() {
        korisnikService.createKorisnik(new Korisnik("admin@admin.com", "admin", passwordEncoder.encode("password"), (byte)1));
        korisnikService.createKorisnik(new Korisnik("ivan@ivan.com", "ivan", passwordEncoder.encode("password"), (byte)0));
        korisnikService.createKorisnik(new Korisnik("pajdo@pajdo.com", "pajdo", passwordEncoder.encode("password"), (byte)0));
        return "Stvoreni novi korisnici: admin, ivan, pajdo (password=password za sve)";
    }
}
