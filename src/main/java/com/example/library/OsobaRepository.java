package com.example.library;

import javafx.collections.FXCollections;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OsobaRepository {
    public void add(Osoba osoba){
        Transaction transaction = null;
        try(Session session= Connecting.openSession()){
            transaction=session.beginTransaction();
            session.persist(osoba);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null && transaction.isActive())
                transaction.rollback();
        }
    }



}
