package com.tn.esprit.gestionmagasinstock.service;

import com.tn.esprit.gestionmagasinstock.entity.Client;
import com.tn.esprit.gestionmagasinstock.enums.ClientCategory;

import java.util.Date;
import java.util.List;

public interface ClientService {
    List<Client> retrieveAllClients();
    Client addClient(Client c);
    void deleteClient(Long id);
    Client updateClient(Client c);
    Client retrieveClient(Long id);
    public float getChiffreAffaireParCategorieClient(ClientCategory clientCategory, Date startDate, Date endDate);
}
