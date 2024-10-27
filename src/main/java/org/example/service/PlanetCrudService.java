package org.example.service;

import org.example.dao.PlanetDao;
import org.example.model.Planet;

import java.util.List;

public class PlanetCrudService {
    PlanetDao planetDao = new PlanetDao();

    public void createPlanet(Planet planet) {
        planetDao.createPlanet(planet);
    }

    public Planet findPlanetById(String id) {
        return planetDao.readPlanetById(id);
    }

    public List<Planet> findAllPlanets() {
        return planetDao.readAllPlanets();
    }

    public void updatePlanet(Planet planet) {
        planetDao.updatePlanet(planet);
    }

    public void updatePlanetById(String id, String newValue) {
        planetDao.updatePlanetById(id, newValue);
    }

    public void deletePlanet(String id) {
        planetDao.deletePlanet(id);
    }
}
