package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KorisnikDetails implements UserDetailsService {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikDetails(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        var korisnik = this.korisnikRepository.findByIme(username);
        if(korisnik.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        var user = korisnik.get();
        return User.withUsername(user.getIme())
                .password(user.getLozinka())
                .roles("USER")
                .build();
    }
}