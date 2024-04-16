package com.tn.esprit.gestionmagasinstock.controller;

import com.tn.esprit.gestionmagasinstock.entity.Client;
import com.tn.esprit.gestionmagasinstock.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/client")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> list = clientService.retrieveAllClients();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
        Client client = clientService.retrieveClient(id);
        return client!=null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }
    @PostMapping("/client")
    public ResponseEntity<Client> CreateClient(@RequestBody Client client){
        Client result = clientService.addClient(client);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
    @PutMapping("/client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client result = clientService.updateClient(client);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

}
