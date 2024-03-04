package DAO;

import model.Client;

import java.util.List;

public interface ClientDAO {
    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients();
}
