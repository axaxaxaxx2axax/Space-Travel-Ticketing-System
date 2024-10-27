package org.example.config;

import lombok.Getter;
import org.example.model.Client;
import org.example.model.Planet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static final HibernateConfig INSTANCE;
    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateConfig();
    }

    private HibernateConfig(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public static HibernateConfig getInstance() {
        return INSTANCE;
    }

    public void close(){
        sessionFactory.close();
    }
}
