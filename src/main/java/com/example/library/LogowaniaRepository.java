package com.example.library;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LogowaniaRepository {
    public boolean logowanie(String username, String password) {
        Transaction transaction = null;
        boolean result = false;
        try (Session session = Connecting.openSession()) {
            transaction = session.beginTransaction();

            // Zapytanie sprawdzające, czy istnieje użytkownik o podanej nazwie i haśle
            Query query = session.createQuery("FROM Logowania WHERE login = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            Logowania user = (Logowania) query.uniqueResult();
            if (user != null) {
                result = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public boolean rejestracja(String username) {
        Transaction transaction = null;
        boolean result = false;
        try (Session session = Connecting.openSession()) {
            transaction = session.beginTransaction();

            // Zapytanie sprawdzające, czy istnieje użytkownik o podanej nazwie i haśle
            Query query = session.createQuery("FROM Logowania WHERE login = :username ");
            query.setParameter("username", username);

            Logowania user = (Logowania) query.uniqueResult();

            // Jeśli zapytanie zwróciło rekord, oznacza to, że podane dane są prawidłowe
            if (user != null) {
                result = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public void add(Logowania logowania) {
        Transaction transaction = null;
        try (Session session = Connecting.openSession()) {
            transaction = session.beginTransaction();
            session.persist(logowania);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

}
