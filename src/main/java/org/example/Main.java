package org.example;


import org.example.config.HibernateConfig;
import org.example.dto.TicketDto;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        createTicketForNotExistOrNullClient();
//        createTicketForNotExistOrNullPlanet();
        TicketCrudService ticketService = new TicketCrudService();
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
//        System.out.println("ticketService.getTicketDtoById(17) = " + ticketService.getTicketDtoById(4));
//        Integer clientId = 3;
//        String fromPlanetId = "EARTH";
//        String toPlanetId = "MARS";

//        ticketService.createTicket(clientId, fromPlanetId, toPlanetId);

//            Ticket ticket = ticketService.getTicketById(3);

//        ticketService.deleteTicket(36);

//        System.out.println(ticketService.getAllTickets());

//        int ticketIdToUpdate = 1;
//        Ticket ticket = ticketService.getTicketById(ticketIdToUpdate);
//
//        if (ticket != null) {
//            System.out.println("Found ticket. Proceeding with update...");
//
//            // Налаштовуємо нові значення для квитка
//            ticket.setCreatedAt(LocalDateTime.now().plusDays(1)); // Наприклад, змінюємо дату створення
//
//            // Якщо необхідно, змінюємо клієнта або планети
//            Client newClient = new Client(); // Приклад нового клієнта, якого вже потрібно додати в БД
//            newClient.setId(2); // Встановлюємо ID існуючого клієнта
//            ticket.setClient(newClient);
//
//            Planet newFromPlanet = new Planet();
//            newFromPlanet.setId("EARTH"); // Приклад зміни планети відправлення
//            ticket.setFromPlanet(newFromPlanet);
//
//            Planet newToPlanet = new Planet();
//            newToPlanet.setId("MARS"); // Приклад зміни планети прибуття
//            ticket.setToPlanet(newToPlanet);
//
//            // Оновлюємо квиток у базі даних
//            ticketService.updateTicket(ticket);
//
//            System.out.println("Ticket updated successfully!");
//        } else {
//            System.out.println("Ticket with ID " + ticketIdToUpdate + " not found.");
//        }

//        System.out.println(ticketService.getAllTickets());

        HibernateConfig.getInstance().close();
    }

    private static void createTicketForNotExistOrNullPlanet() {
        TicketCrudService ticketService = new TicketCrudService();
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();

        Integer clientId = 13;
        String fromPlanetId = null;
        String toPlanetId = "VENUS";

        Client client = clientService.findClientById(clientId);
        if (client == null) {
            System.out.println("Client with ID " + clientId + " does not exist.");
            return;
        }

        Planet fromPlanet = fromPlanetId != null ? planetService.findPlanetById(fromPlanetId) : null;
        Planet toPlanet = toPlanetId != null ? planetService.findPlanetById(toPlanetId) : null;

        if (fromPlanet == null || toPlanet == null) {
            System.out.println("Invalid ticket creation request:");
            if (fromPlanet == null) {
                System.out.println("From planet with ID " + fromPlanetId + " does not exist or is null.");
            }
            if (toPlanet == null) {
                System.out.println("To planet with ID " + toPlanetId + " does not exist or is null.");
            }
            return;
        }

        Ticket ticket = Ticket.builder()
                .createdAt(LocalDateTime.now())
                .client(client)
                .fromPlanet(fromPlanet)
                .toPlanet(toPlanet)
                .build();

        Ticket createdTicket = ticketService.createTicket(clientId, fromPlanetId, toPlanetId);

        if (createdTicket != null) {
            System.out.println("Ticket created successfully: ID=" + createdTicket.getId());
        } else {
            System.out.println("Ticket creation failed.");
        }
    }

    private static void createTicketForNotExistOrNullClient() {
        TicketCrudService ticketService = new TicketCrudService();
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();

        Client client = clientService.findClientById(0);
        if (client == null) {
            System.out.println("Client with ID 0 does not exist. Cannot create ticket.");
            return;
        }

        Planet fromPlanet = planetService.findPlanetById("MARS");
        Planet toPlanet = planetService.findPlanetById("EARTH");

        if (fromPlanet == null || toPlanet == null) {
            System.out.println("One or both planets do not exist. Cannot create ticket.");
            return;
        }

        Ticket createdTicket = ticketService.createTicket(client.getId(), fromPlanet.getId(), toPlanet.getId());
        if (createdTicket != null) {
            System.out.println("Ticket creation successful: id=" + createdTicket.getId());
        } else {
            System.out.println("Failed to create ticket.");
        }
    }

}