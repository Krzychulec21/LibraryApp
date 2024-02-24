package com.example.library.model;

import jakarta.persistence.*;

@Entity
public class Wydawnictwo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wydawnictwo;

    public Wydawnictwo(Long id, String wydawnictwo) {
        this.id = id;
        this.wydawnictwo = wydawnictwo;
    }

    public Wydawnictwo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    @Override
    public String toString() {
        return wydawnictwo;
    }
}
