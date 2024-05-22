package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CreateEditOglasDTO {

    @NotBlank(message = "Naziv cannot be blank")
    private String naziv;

    @NotBlank(message = "Opis cannot be blank")
    private String opis;

    @NotNull(message = "Pocetna Cijena cannot be null")
    private Float pocetnaCijena;

    @NotNull(message = "Pocetno Vrijeme cannot be null")
    private LocalDateTime pocetnoVrijeme;

    @NotNull(message = "Zavrsno Vrijeme cannot be null")
    private LocalDateTime zavrsnoVrijeme;

    @NotNull(message = "Mjesto cannot be null")
    private Long mjesto;

    @NotNull(message = "Kategorije cannot be null")
    private List<Long> kategorije;

    public @NotBlank(message = "Naziv cannot be blank") String getNaziv() {
        return naziv;
    }

    public void setNaziv(@NotBlank(message = "Naziv cannot be blank") String naziv) {
        this.naziv = naziv;
    }

    public @NotBlank(message = "Opis cannot be blank") String getOpis() {
        return opis;
    }

    public void setOpis(@NotBlank(message = "Opis cannot be blank") String opis) {
        this.opis = opis;
    }

    public @NotNull(message = "Pocetna Cijena cannot be null") Float getPocetnaCijena() {
        return pocetnaCijena;
    }

    public void setPocetnaCijena(@NotNull(message = "Pocetna Cijena cannot be null") Float pocetnaCijena) {
        this.pocetnaCijena = pocetnaCijena;
    }

    public @NotNull(message = "Pocetno Vrijeme cannot be null") LocalDateTime getPocetnoVrijeme() {
        return pocetnoVrijeme;
    }

    public void setPocetnoVrijeme(@NotNull(message = "Pocetno Vrijeme cannot be null") LocalDateTime pocetnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
    }

    public @NotNull(message = "Zavrsno Vrijeme cannot be null") LocalDateTime getZavrsnoVrijeme() {
        return zavrsnoVrijeme;
    }

    public void setZavrsnoVrijeme(@NotNull(message = "Zavrsno Vrijeme cannot be null") LocalDateTime zavrsnoVrijeme) {
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }

    public @NotNull(message = "Mjesto cannot be null") Long getMjesto() {
        return mjesto;
    }

    public void setMjesto(@NotNull(message = "Mjesto cannot be null") Long mjesto) {
        this.mjesto = mjesto;
    }

    public @NotNull(message = "Kategorije cannot be null") List<Long> getKategorije() {
        return kategorije;
    }

    public void setKategorije(@NotNull(message = "Kategorije cannot be null") List<Long> kategorije) {
        this.kategorije = kategorije;
    }
}
