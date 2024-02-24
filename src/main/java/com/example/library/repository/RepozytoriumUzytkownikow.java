package com.example.library.repository;

import com.example.library.model.DaneUzytkownika;
import com.example.library.util.PolaczenieBazowe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RepozytoriumUzytkownikow {
    public boolean zaloguj(String nazwaUzytkownika, String haslo) {
        Transaction transakcja = null;
        boolean wynik = false;
        try (Session sesja = PolaczenieBazowe.otworzSesje()) {
            transakcja = sesja.beginTransaction();

            Query zapytanie = sesja.createQuery("FROM DaneUzytkownika WHERE login = :nazwaUzytkownika AND haslo = :haslo");
            zapytanie.setParameter("nazwaUzytkownika", nazwaUzytkownika);
            zapytanie.setParameter("haslo", haslo);
            DaneUzytkownika uzytkownik = (DaneUzytkownika) zapytanie.uniqueResult();
            if (uzytkownik != null) {
                wynik = true;
            }
            transakcja.commit();
        } catch (Exception e) {
            if (transakcja != null) {
                transakcja.rollback();
            }
            e.printStackTrace(); // Tutaj powinno być logowanie błędu zamiast wydruku stosu.
        }
        return wynik;
    }

    public boolean zarejestruj(String nazwaUzytkownika) {
        Transaction transakcja = null;
        boolean wynik = false;
        try (Session sesja = PolaczenieBazowe.otworzSesje()) {
            transakcja = sesja.beginTransaction();
            Query zapytanie = sesja.createQuery("FROM DaneUzytkownika WHERE login = :nazwaUzytkownika ");
            zapytanie.setParameter("nazwaUzytkownika", nazwaUzytkownika);
            DaneUzytkownika uzytkownik = (DaneUzytkownika) zapytanie.uniqueResult();
            if (uzytkownik != null) {
                wynik = true;
            }
            transakcja.commit();
        } catch (Exception e) {
            if (transakcja != null) {
                transakcja.rollback();
            }
            e.printStackTrace(); // Tutaj powinno być logowanie błędu zamiast wydruku stosu.
        }
        return wynik;
    }

    public void dodaj(DaneUzytkownika daneUzytkownika) {
        Transaction transakcja = null;
        try (Session sesja = PolaczenieBazowe.otworzSesje()) {
            transakcja = sesja.beginTransaction();
            sesja.persist(daneUzytkownika);
            transakcja.commit();
        } catch (Exception e) {
            if (transakcja != null && transakcja.isActive())
                transakcja.rollback();
            e.printStackTrace(); // Tutaj powinno być logowanie błędu zamiast wydruku stosu.
        }
    }
}
