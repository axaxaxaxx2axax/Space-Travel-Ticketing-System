package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void createClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        }
    }

    public Client readClientById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, id);
            return client;
        }
    }

    public List<Client> readAllClients() {
        try (Session session = sessionFactory.openSession()) {
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            return clients;
        }
    }

    public void updateClient(Client client) {
        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            session.update(client);
            tx.commit();
        }
    }

    public void updateClientById(int id, String newValue) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(newValue);
                session.update(client);
                tx.commit();
            } else {
                System.out.println("Client with id " + id + " not found");
            }
        }
    }

    public void deleteClient(int id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
            }
            tx.commit();
        }
    }
}
