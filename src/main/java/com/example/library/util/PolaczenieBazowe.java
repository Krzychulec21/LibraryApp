package com.example.library.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PolaczenieBazowe {
    private static SessionFactory sessionFactory = null;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static Session otworzSesje() {
        return sessionFactory.openSession();
    }

}
