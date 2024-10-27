package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.example.model.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void createPlanet(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(planet);
            tx.commit();
        }
    }

    public Planet readPlanetById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Planet planet = session.get(Planet.class, id);
            return planet;
        }
    }

    public List<Planet> readAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
            return planets;
        }
    }

    public void updatePlanet(Planet planet) {
        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            session.update(planet);
            tx.commit();
        }
    }

    public void updatePlanetById(String id, String newValue) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(newValue);
                session.update(planet);
                tx.commit();
            } else {
                System.out.println("Planet with id " + id + " not found");
            }
        }
    }

    public void deletePlanet(String id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
            }
            tx.commit();
        }
    }
}
