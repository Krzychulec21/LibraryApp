package com.example.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connecting {
    private static SessionFactory sessionFactory = null;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}
