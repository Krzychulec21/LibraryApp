package com.example.library.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
    public class Autor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String imie;
        private String nazwisko;

    public Autor(long id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Autor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return imie+" "+nazwisko;
    }
}


