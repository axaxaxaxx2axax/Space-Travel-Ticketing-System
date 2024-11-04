package org.example.service;

import org.example.dao.TicketDao;
import org.example.dto.TicketDto;
import org.example.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TicketCrudService {
    TicketDao ticketDao = new TicketDao();
    private static final Logger logger = LoggerFactory.getLogger(TicketCrudService.class);

    public Ticket createTicket(Integer clientId, String fromPlanetId, String toPlanetId) {
        Ticket ticket = ticketDao.createTicket(clientId, fromPlanetId, toPlanetId);

        if (ticket == null) {
            logger.error("Failed to create ticket with provided IDs: clientId={}, fromPlanetId={}, toPlanetId={}", clientId, fromPlanetId, toPlanetId);
            return null;
        }

        logger.info("Ticket created successfully in service: id={}, createdAt={}, clientId={}, fromPlanetId={}, toPlanetId={}",
                ticket.getId(), ticket.getCreatedAt(), clientId, fromPlanetId, toPlanetId);

        return ticket;
    }

    public TicketDto getTicketDtoById(int id) {
        TicketDto ticketDto = ticketDao.readTicketById(id);
        if (ticketDto == null) {
            logger.warn("Ticket with id {} not found in service", id);
        }
        return ticketDto;
    }

    public Ticket getTicketById(int id) {
        if (id <= 0) {
            logger.error("Invalid ticket ID: {}", id);
            return null;
        }

        Ticket ticket = ticketDao.readTicketByIdAsTicket(id);
        if (ticket != null) {
            logger.info("Retrieved ticket: {}", ticket);
        } else {
            logger.warn("No ticket found with id: {}", id);
        }

        return ticket;

    }

    public List<TicketDto> getAllTickets() {
        List<TicketDto> ticketDtos = ticketDao.readAllTickets();
        if (ticketDtos == null || ticketDtos.isEmpty()) {
            logger.warn("No tickets found in service.");
        } else {
            logger.info("Retrieved {} tickets in service.", ticketDtos.size());
        }
        return ticketDtos;
    }

    public boolean updateTicket(Ticket ticket) {
        if (ticket == null || ticket.getId() <= 0) {
            logger.error("Invalid ticket data for update: {}", ticket);
            return false;
        }

        ticketDao.updateTicket(ticket);
        logger.info("Ticket updated successfully in service: {}", ticket);
        return true;
    }

    public boolean deleteTicket(int id) {
        int deletedCount = ticketDao.deleteTicketById(id);
        if (deletedCount > 0) {
            logger.info("Ticket with id {} deleted successfully in service.", id);
            return true;
        } else {
            logger.warn("Ticket with id {} not found for deletion in service.", id);
            return false;
        }
    }
}

