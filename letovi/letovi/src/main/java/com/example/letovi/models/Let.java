package com.example.letovi.models;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "let")
public class Let {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "polazni_aerodrom")
    private String polazniAerodrom;

    @Column(name = "odredisni_aerodrom")
    private String odredisniAerodrom;

    @Column(name = "datum_polaska")
    private LocalDate datumPolaska;

    @Column(name = "datum_povratka")
    private LocalDate datumPovratka;

    @Column(name = "broj_putnika")
    private Integer brojPutnika;

    @Column(name = "cijena")
    private double cijena;

    @Column(name ="ruta_polaska")
    private String rutaPolaska;

    @Column(name ="ruta_povratka")
    private String rutaPovratka;

    @Column(name = "valuta")
    private String valuta;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(String polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public String getOdredisniAerodrom() {
        return odredisniAerodrom;
    }

    public void setOdredisniAerodrom(String odredisniAerodrom) {
        this.odredisniAerodrom = odredisniAerodrom;
    }

    public LocalDate getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(LocalDate datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public LocalDate getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(LocalDate datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public Integer getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(Integer brojPutnika) {
        this.brojPutnika = brojPutnika;
    }

    public String getRutaPolaska() {
        return rutaPolaska;
    }

    public void setRutaPolaska(String rutaPolaska) {
        this.rutaPolaska = rutaPolaska;
    }

    public String getRutaPovratka() {
        return rutaPovratka;
    }

    public void setRutaPovratka(String rutaPovratka) {
        this.rutaPovratka = rutaPovratka;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

}
