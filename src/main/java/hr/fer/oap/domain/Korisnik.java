package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"}),
        @UniqueConstraint(name = "ime", columnNames = {"ime"})
})
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraKorisnik", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "ime", nullable = false, length = 20)
    private String ime;

    @Column(name = "lozinka", nullable = false, length = 200)
    private String lozinka;

    @Column(name = "admin", nullable = false)
    private Byte admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Byte getAdmin() {
        return admin;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
    }

}