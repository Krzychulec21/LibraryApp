package com.example.library.model;

import jakarta.persistence.*;

@Entity
public class Ksiazka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tytul;
    private String rok;
    private String okladka;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Autor autor;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Wydawnictwo wydawnictwo;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Gatunek gatunek;
    String status;

    public Ksiazka(long id, String tytul, String rok, String okladka, Autor autor, Wydawnictwo wydawnictwo, Gatunek gatunek,String status) {
        this.id = id;
        this.tytul = tytul;
        this.rok = rok;
        this.okladka = okladka;
        this.autor = autor;
        this.wydawnictwo = wydawnictwo;
        this.gatunek = gatunek;
        this.status = status;
    }

    public Ksiazka() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public String getOkladka() {
        return okladka;
    }

    public void setOkladka(String okladka) {
        this.okladka = okladka;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Wydawnictwo getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(Wydawnictwo wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                ", rok='" + rok + '\'' +
                ", okladka='" + okladka + '\'' +
                ", autor=" + autor +
                ", wydawnictwo=" + wydawnictwo +
                ", gatunek=" + gatunek +
                ", status='" + status + '\'' +
                '}';
    }
}

