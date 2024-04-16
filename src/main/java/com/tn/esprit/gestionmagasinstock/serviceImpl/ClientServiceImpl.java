package com.tn.esprit.gestionmagasinstock.serviceImpl;

import com.tn.esprit.gestionmagasinstock.dao.ClientDao;
import com.tn.esprit.gestionmagasinstock.entity.Client;
import com.tn.esprit.gestionmagasinstock.enums.ClientCategory;
import com.tn.esprit.gestionmagasinstock.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;
    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientDao.findAll();
    }

    @Override
    public Client addClient(Client c) {
        return clientDao.save(c);
    }

    @Override
    public void deleteClient(Long id) {
        Optional<Client> optionalClient =clientDao.findById(id);
        if(optionalClient.isPresent()){
            clientDao.deleteById(id);
        }
        System.out.println("Error : Client doesn't exist.");
    }

    @Override
    public Client updateClient(Client c) {
        return clientDao.save(c);
    }

    @Override
    public Client retrieveClient(Long id) {
        Optional<Client> optionalClient =clientDao.findById(id);
        if(optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;
    }

    @Override
    public float getChiffreAffaireParCategorieClient(ClientCategory clientCategory, Date startDate, Date endDate) {
       return clientDao.calculeChifreDaffaireByCategorie(clientCategory,startDate,endDate);
    }
}
