package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.dto.TicketDto;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(TicketDao.class);

    public Ticket createTicket(Integer clientId, String fromPlanetId, String toPlanetId) {
        Transaction tx = null;
        Ticket ticket = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Client client = new Client();
            client.setId(clientId);

            Planet fromPlanet = new Planet();
            fromPlanet.setId(fromPlanetId);

            Planet toPlanet = new Planet();
            toPlanet.setId(toPlanetId);

            ticket = new Ticket();
            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            ticket.setCreatedAt(LocalDateTime.now());

            session.save(ticket);
            tx.commit();

            logger.info("Ticket created successfully in DAO: id={}, createdAt={}, clientId={}, fromPlanetId={}, toPlanetId={}",
                    ticket.getId(), ticket.getCreatedAt(), clientId, fromPlanetId, toPlanetId);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            logger.error("Failed to create ticket with IDs: clientId={}, fromPlanetId={}, toPlanetId={}", clientId, fromPlanetId, toPlanetId, e);
        }
        return ticket;
    }

    public TicketDto readTicketById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TicketDto ticketDto = session.createQuery(
                            "SELECT new org.example.dto.TicketDto(t.id, t.createdAt, c.name, fp.name, tp.name) " +
                                    "FROM Ticket t " +
                                    "JOIN t.client c " +
                                    "JOIN t.fromPlanet fp " +
                                    "JOIN t.toPlanet tp " +
                                    "WHERE t.id = :id", TicketDto.class)
                    .setParameter("id", id)
                    .uniqueResult();
            logger.info("Ticket DTO found: {}", ticketDto);
            return ticketDto;
        } catch (Exception e) {
            logger.error("Failed to read ticket by id: {}", id, e);
            return null;
        }
    }

    public Ticket readTicketByIdAsTicket(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT t FROM Ticket t " +
                                    "JOIN FETCH t.client " +
                                    "JOIN FETCH t.fromPlanet " +
                                    "JOIN FETCH t.toPlanet " +
                                    "WHERE t.id = :id", Ticket.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

public List<TicketDto> readAllTickets() {
    try (Session session = sessionFactory.openSession()) {
        List<TicketDto> ticketDtos = session.createQuery("SELECT new org.example.dto.TicketDto(t.id, t.createdAt, c.name, fp.name, tp.name)" +
                "FROM Ticket t " +
                "JOIN t.client c " +
                "JOIN t.fromPlanet fp " +
                "JOIN t.toPlanet tp", TicketDto.class).list();
        logger.info("Retrieved {} tickets", ticketDtos.size());
        return ticketDtos;
    } catch (Exception e) {
        logger.error("Failed to retrieve all tickets", e);
        return null;
    }
}

public void updateTicket(Ticket ticket) {
    Transaction tx = null;
    try (Session session = sessionFactory.openSession()) {
        tx = session.beginTransaction();
        session.update(ticket);
        tx.commit();
        logger.info("Ticket updated successfully: id={}, createdAt={}, clientId={}, fromPlanetId={}, toPlanetId={}",
                ticket.getId(), ticket.getCreatedAt(), ticket.getClient().getId(), ticket.getFromPlanet().getId(), ticket.getToPlanet().getId());
    } catch (Exception e) {
        if (tx != null) tx.rollback();
        logger.error("Failed to update ticket: id={}, createdAt={}, clientId={}, fromPlanetId={}, toPlanetId={}",
                ticket.getId(), ticket.getCreatedAt(), ticket.getClient().getId(), ticket.getFromPlanet().getId(), ticket.getToPlanet().getId(), e);
    }
}

    public int deleteTicketById(int id) {
        Transaction tx = null;
        int deletedCount = 0;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            deletedCount = session.createQuery("DELETE FROM Ticket t WHERE t.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            if (deletedCount > 0) {
                logger.info("Ticket deleted successfully with id: {}", id);
            } else {
                logger.warn("Ticket with id {} not found for deletion", id);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Failed to delete ticket with id: {}", id, e);
        }
        return deletedCount;
    }
}

