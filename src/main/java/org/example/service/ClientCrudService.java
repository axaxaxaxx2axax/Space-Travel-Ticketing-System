package org.example.service;

import org.example.dao.ClientDao;
import org.example.model.Client;

import java.util.List;

public class ClientCrudService {
    ClientDao clientDao = new ClientDao();

    public void createClient(Client client) {
        clientDao.createClient(client);
    }

    public Client findClientById(int id) {
        return clientDao.readClientById(id);
    }

    public List<Client> findAllClients() {
        return clientDao.readAllClients();
    }

    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    public void updateClientById(int id, String newValue) {
        clientDao.updateClientById(id, newValue);
    }

    public void deleteClient(int id) {
        clientDao.deleteClient(id);
    }
}
