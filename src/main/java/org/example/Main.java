package org.example;

import org.example.config.DataSource;
import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;


public class Main {
    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        Client client = new Client();

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planet = new Planet();

//        client.setName("Anastasiia");
//        clientCrudService.createClient(client);
//
//        client.setName("Meow");
//        clientCrudService.updateClient(client);

//        clientCrudService.updateClientById(11, "Anastasiia Izotova");
//        clientCrudService.deleteClient(11);

//        planet.setId("MEOW78");
//        planet.setName("CAT");
//        planetCrudService.createPlanet(planet);
//        System.out.println(planetCrudService.findPlanetById(planet.getId()));

//        Planet updatePlanet = planetCrudService.findPlanetById("MEOW78");
//        updatePlanet.setName("Meow");
//        planetCrudService.updatePlanet(updatePlanet);
//        planetCrudService.deletePlanet("MEOW78");


        System.out.println(clientCrudService.findAllClients());
        System.out.println(planetCrudService.findAllPlanets());

        HibernateConfig.getInstance().close();
    }
}