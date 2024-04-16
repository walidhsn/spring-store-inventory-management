package com.tn.esprit.gestionmagasinstock.controller;

import com.tn.esprit.gestionmagasinstock.entity.Invoice;
import com.tn.esprit.gestionmagasinstock.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    private final InvoiceService invoiceService;
    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping("/invoice/{id}")
    public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable("id") Long clientId){
        List<Invoice> invoiceList = invoiceService.getFacturesByClient(clientId);
        return ResponseEntity.ok(invoiceList);
    }
}
