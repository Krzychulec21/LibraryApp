package com.example.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Wypozyczenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Osoba osoba;
    @ManyToOne
    private Ksiazka ksiazka;
    private LocalDate dataWypozyczenia;
    private LocalDate dataOddania;

    public Wypozyczenia(long id, Osoba osoba, Ksiazka ksiazka, LocalDate dataWypozyczenia, LocalDate dataOddania) {
        this.id = id;
        this.osoba = osoba;
        this.ksiazka = ksiazka;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataOddania = dataOddania;
    }

    public Wypozyczenia() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    public LocalDate getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(LocalDate dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public LocalDate getDataOddania() {
        return dataOddania;
    }

    public void setDataOddania(LocalDate dataOddania) {
        this.dataOddania = dataOddania;
    }


    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "id=" + id +
                ", osoba=" + osoba +
                ", ksiazka=" + ksiazka +
                ", dataWypozyczenia=" + dataWypozyczenia +
                ", dataOddania=" + dataOddania +
                '}';
    }

    //dodatkowe gettery do obslugi glowenj tabeli wypozyczenia
    public long getIdKsiazki() {
        return ksiazka.getId();
    }
    public String getTytulKsiazki(){return ksiazka.getTytul();}
    public Autor getNazwaAutora(){return ksiazka.getAutor();}
    public String getImieOsoby(){return osoba.getImie();}
    public String getNazwiskoOsoby(){return osoba.getNazwisko();}
    public Long getIdOsoby(){return osoba.getId();}
    public void setStatusKsiazki(Ksiazka ksiazka) {

    }

}
