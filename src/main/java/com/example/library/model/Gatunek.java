package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gatunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     private String gatunek;

    public Gatunek(long id, String gatunek) {
        this.id = id;
        this.gatunek = gatunek;
    }

    public Gatunek() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    @Override
    public String toString() {
        return gatunek;
    }
}
